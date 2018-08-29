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

	private Command localDriveStraight = new DriveStraight(Robot.distanceWallToAuto);
	
    public DriveToAuto() {
    	requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	SmartDashboard.putString("Status", "Driving to Auto Line");
    	localDriveStraight.start();
    }

    protected boolean isFinished() {
        return localDriveStraight.isCompleted();
    }
    
    protected void end() {
    	SmartDashboard.putString("Status", "Waiting for next command");
    }
}
