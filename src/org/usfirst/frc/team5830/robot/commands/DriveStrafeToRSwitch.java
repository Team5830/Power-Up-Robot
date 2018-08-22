package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveStrafeToRSwitch extends Command {
	
	private boolean isItFinished = false;

    public DriveStrafeToRSwitch() {
    	requires(Robot.swerveDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.wheelEncoder1.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.distanceCWallToSwitch - RobotMap.wheelEncoder1.getDistance()) < 2) Robot.swerveDrive.drive(0.25, 0.5, 0);
    	else {
    		Robot.swerveDrive.drive(0, 0, 0);
    		isItFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isItFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	isItFinished = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	isItFinished = false;
    }
}
