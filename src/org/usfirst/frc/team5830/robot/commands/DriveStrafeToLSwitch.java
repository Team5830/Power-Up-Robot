package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveStrafeToLSwitch extends Command {
	
	private boolean isItFinished = false;

    public DriveStrafeToLSwitch() {
    	requires(Robot.swerveDrive);
    }

    protected void initialize() {
    	RobotMap.wheelEncoder1.reset();
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Driving to Left Switch");
    	if(Math.abs(Robot.distanceCWallToSwitch - RobotMap.wheelEncoder1.getDistance()) < 2) Robot.swerveDrive.drive(-0.25, 0.5, 0);
    	else {
    		Robot.swerveDrive.drive(0, 0, 0);
    		isItFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isItFinished;
    }

    protected void end() {
        SmartDashboard.putString("Status", "Waiting for next command");
        isItFinished = false;
    }

    protected void interrupted() {
    	isItFinished = false;
    }
}
