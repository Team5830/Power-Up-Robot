package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetToZero extends Command {

    public ResetToZero() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.auto_GYRO_Correction_Swerve.setSetpoint(0);
    	Robot.auto_GYRO_Correction_Swerve.setOutputRange(-0.5, 0.5);
    	Robot.auto_GYRO_Correction_Swerve.enable();
    	Robot.swerveDrive.drive(0, 0, Robot.pidOutputAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) < 20;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, Robot.rotX);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
