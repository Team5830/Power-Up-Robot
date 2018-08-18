package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchRelease extends Command {
	
	boolean isItFinished = false;

    public WinchRelease() {
    	requires(Robot.WINCHPID);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.WINCHPID.setSetpoint(-500);
    	Robot.WINCHPID.enable();
    	Timer.delay(2);
    	Robot.WINCHPID.setSetpoint(0);
    	Robot.WINCHPID.disable();
    	isItFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isItFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
