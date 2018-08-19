package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveRotateToNeg90 extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveRotateToNeg90() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveMotionProfiling.driveAngle = -90;
    	DriveMotionProfiling.driveDistance = 0;
    	localDriveMotionProfiling.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
}
