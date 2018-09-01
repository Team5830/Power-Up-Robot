package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveToScale extends Command {

	private Command localDriveStraight = new DriveStraight(Robot.distanceWallToScale);
	
    public DriveToScale() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	SmartDashboard.putString("Status", "Driving to Scale");
    	localDriveStraight.start();
    }

    protected boolean isFinished() {
        return DriveStraight.isItFinished;
    }
    
    protected void end() {
    	SmartDashboard.putString("Status", "Waiting for next command");
    }
}
