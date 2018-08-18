package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CrossAuto extends Command {
	//boolean isItFinished;

    public CrossAuto() {
    	requires(Robot.auto_LIDAR_Distance_Swerve);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Autonomous Status", "Crossing Auto");
    	//isItFinished = false;
    	
    	Robot.auto_GYRO_Correction_Swerve.setSetpoint(0);
    	Robot.auto_GYRO_Correction_Swerve.setOutputRange(-0.05, 0.05);
    	Robot.auto_GYRO_Correction_Swerve.enable();
    	Robot.auto_LIDAR_Distance_Swerve.setSetpoint(Robot.distanceWallToAuto);
    	Robot.auto_LIDAR_Distance_Swerve.enable();
    	Robot.swerveDrive.drive(0, -Robot.pidOutputRobot, Robot.pidOutputAngle);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.auto_LIDAR_Distance_Swerve.getSetpoint() - Robot.auto_LIDAR_Distance_Swerve.getPosition()) < 5; //Ends command when robot is 5 inches away from setpoint
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous Status", "END");
    	Robot.auto_LIDAR_Distance_Swerve.disable();
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
