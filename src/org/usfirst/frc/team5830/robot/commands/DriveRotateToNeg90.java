package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveRotateToNeg90 extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveRotateToNeg90() {
        requires(Robot.swerveDrive);
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
