package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveToAuto extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveToAuto() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	DriveMotionProfiling.driveAngle = 0;
    	DriveMotionProfiling.driveDistance = Robot.distanceWallToAuto;
    	localDriveMotionProfiling.start();
    }

    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
}
