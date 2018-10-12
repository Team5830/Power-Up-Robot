package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class WinchDown extends Command {

    public WinchDown() {
    	requires(Robot.WINCHPID);
    }

    protected void execute() {
    	Robot.WINCHPID.setSetpoint(2000);
    	Robot.WINCHPID.enable();
    }

    protected boolean isFinished() {
        return Math.abs(Robot.WINCHPID.getSetpoint() - Robot.WINCHPID.getPosition()) < 500;
    }
}
