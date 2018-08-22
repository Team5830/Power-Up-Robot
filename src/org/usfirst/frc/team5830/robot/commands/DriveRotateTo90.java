package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveRotateTo90 extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveRotateTo90() {
        requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	DriveMotionProfiling.driveAngle = 90;
    	DriveMotionProfiling.driveDistance = 0;
    	localDriveMotionProfiling.start();
    }

    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
}
