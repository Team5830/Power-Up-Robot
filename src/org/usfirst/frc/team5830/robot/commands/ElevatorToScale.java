package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class ElevatorToScale extends Command {

    public ElevatorToScale() {
    	requires(Robot.ELEVATORPID);
    }

    protected void execute() {
    	Robot.ELEVATORPID.setSetpoint(6000);
    	Robot.ELEVATORPID.enable();
    }

    protected boolean isFinished() {
        return Math.abs(Robot.ELEVATORPID.getSetpoint() - Robot.ELEVATORPID.getPosition()) < 500;
    }
}
