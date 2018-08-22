package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchUp extends Command {

    public WinchUp() {
    	requires(Robot.WINCHPID);
    }

    protected void execute() {
    	Robot.WINCHPID.setSetpoint(0);
    	Robot.WINCHPID.enable();
    }

    protected boolean isFinished() {
        return Math.abs(Robot.WINCHPID.getSetpoint() - Robot.WINCHPID.getPosition()) < 500;
        /*
         * I assume that since we want the robot to start with the manipulator already wound up the 
         * up position should be zero on the encoder.
         */
    }
}
