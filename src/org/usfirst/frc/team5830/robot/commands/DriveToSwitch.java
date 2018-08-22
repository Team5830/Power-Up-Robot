package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToSwitch extends Command {

	private Command localDriveMotionProfiling = new DriveMotionProfiling();
	
    public DriveToSwitch() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	SmartDashboard.putString("Status", "Driving to Switch");
    	DriveMotionProfiling.driveAngle = 0;
    	DriveMotionProfiling.driveDistance = Robot.distanceWallToSwitch;
    	localDriveMotionProfiling.start();
    }

    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
    
    protected void end() {
    	SmartDashboard.putString("Status", "Waiting for next command");
    }
}
