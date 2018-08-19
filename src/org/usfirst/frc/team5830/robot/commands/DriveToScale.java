package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToScale extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveToScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveMotionProfiling.driveAngle = 0;
    	DriveMotionProfiling.driveDistance = Robot.distanceWallToScale;
    	localDriveMotionProfiling.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
}
