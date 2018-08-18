package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TEMPCCrossAuto extends Command {

    public TEMPCCrossAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.auto_GYRO_Correction_Swerve.setSetpoint(0);
    	Robot.auto_GYRO_Correction_Swerve.setOutputRange(-0.05, 0.05);
    	Robot.auto_GYRO_Correction_Swerve.enable();
    	Robot.auto_LIDAR_Distance_Swerve.setSetpoint(Robot.distanceWallToCAuto);
    	Robot.auto_LIDAR_Distance_Swerve.enable();
    	Robot.swerveDrive.drive(0, -Robot.pidOutputRobot, -Robot.pidOutputAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.auto_LIDAR_Distance_Swerve.getSetpoint() - Robot.auto_LIDAR_Distance_Swerve.getPosition()) < 15;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.auto_LIDAR_Distance_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.auto_LIDAR_Distance_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }
}
