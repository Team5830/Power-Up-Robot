package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveCloserToSwitch extends InstantCommand {

	private boolean isItFinished = false;
	
    public DriveCloserToSwitch() {
        requires(Robot.swerveDrive);
    }
    
    
    protected void execute() {
    	Robot.swerveDrive.drive(0, -0.15, 0);
    	Timer.delay(2);
    	Robot.swerveDrive.drive(0, 0, 0);
    	isItFinished = true;
    }
    
    protected boolean isFinished() {
    	return isItFinished;
    }
    
    protected void end() {
    	isItFinished = false;
    }
}
