package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter Pugh
 * 
 */

/**
 * How to use this command for autonomous:
 * Call command like:
 * 		new MotionProfiling(<angle>, <distance>);
 * <angle> refers to the absolute angle setpoint in degrees, a value between -180 and 180 with 0 facing directly away from the alliance wall.
 * <distance> refers to the amount in inches the robot will move after rotating to the <angle>.
 */
public class DriveRotationSetpoint extends Command {
	
	private double driveSetpointAngle;

	//Negative angle numbers allowed. Negative distance numbers NOT ALLOWED. Instead, have the robot rotate away then move forward.
	
	//I (Hunter P.) would have LOVED to make the variables set directly when command is called, but CommandGroups don't support
	//two of the same command but with different variables. Stupid. Now they have to be set separately in another command.
    public DriveRotationSetpoint(double angle) {
    	super ("DriveMotionProfiling");
    	requires(Robot.swerveDrive);
    	driveSetpointAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Status", "Automatically Driving");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
        	Robot.auto_GYRO_Correction_Swerve.setSetpoint(driveSetpointAngle);
        	Robot.auto_GYRO_Correction_Swerve.enable();
        	Robot.swerveDrive.drive(0, 0, Robot.pidOutputAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Only is finished when angle setpoint met
        return Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) < 5 && Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) > (0 - 3);
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {    	
    	//Disables all associated PID loops
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }
}
