package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putString("Status", "Driving to Auto Line");
    	DriveMotionProfiling.driveAngle = 0;
    	DriveMotionProfiling.driveDistance = Robot.distanceWallToAuto;
    	localDriveMotionProfiling.start();
    }

    protected boolean isFinished() {
        return localDriveMotionProfiling.isCompleted();
    }
    
    protected void end() {
    	SmartDashboard.putString("Status", "Waiting for next command");
    }
}
