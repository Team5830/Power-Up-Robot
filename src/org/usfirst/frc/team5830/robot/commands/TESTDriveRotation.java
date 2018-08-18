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
public class TESTDriveRotation extends Command {
	
	//private int loopCount = 0;
	private double localAngleDriveRotation;

	//Negative angle numbers allowed. Negative distance numbers NOT ALLOWED. Instead, have the robot rotate away then move forward.
    public TESTDriveRotation(double angle) {//TODO Make sure variables get passed properly
    	super ("TESTDriveRotation");
    	requires(Robot.swerveDrive);
    	localAngleDriveRotation = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Status", "Automatically Driving");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Auto Rotation Setpoint", localAngleDriveRotation);
    	//Gets the angle requested by the command start variable and sets the PID loop to it, then enables it
    	Robot.auto_GYRO_Correction_Swerve.setSetpoint(localAngleDriveRotation);
    	Robot.auto_GYRO_Correction_Swerve.enable();
    	Robot.swerveDrive.drive(0, 0, Robot.pidOutputAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Only is finished when both distance and angle setpoints have been met
        return (Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) < 5);
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Resets loop counter to 0
    	//loopCount = 0;
    	
    	//Disables all associated PID loops
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.WHEELDISTANCEPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
