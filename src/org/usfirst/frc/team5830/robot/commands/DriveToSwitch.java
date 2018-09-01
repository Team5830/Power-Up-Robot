package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveToSwitch extends Command {

	private Command localDriveStraight = new DriveStraight(Robot.distanceWallToSwitch);
	
    public DriveToSwitch() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	SmartDashboard.putString("Status", "Driving to Auto Line");
    	localDriveStraight.start();
    }

    protected boolean isFinished() {
        return DriveStraight.isItFinished;
    }
    
    protected void end() {
    	SmartDashboard.putString("Status", "Waiting for next command");
    }
}
