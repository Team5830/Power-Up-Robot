package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToSwitch extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveToSwitch() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	DriveMotionProfiling.driveAngle = 0;
    	DriveMotionProfiling.driveDistance = Robot.distanceWallToSwitch;
    	localDriveMotionProfiling.start();
    }

    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
}
