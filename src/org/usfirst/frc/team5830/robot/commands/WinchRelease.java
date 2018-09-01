package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P.
 *
 */
public class WinchRelease extends InstantCommand {

    public WinchRelease() {
    	requires(Robot.WINCHPID);
    }

    protected void execute() {
    	Robot.WINCHPID.setSetpoint(-500);
    	Robot.WINCHPID.enable();
    }
}
