package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ElevatorReleasePID extends InstantCommand {

    public ElevatorReleasePID() {
    	requires(Robot.ELEVATORPID);
    	requires(Robot.WINCHPID);
    }

    protected void execute() {
    	//There is no isFinished defined because this is an InstantCommand. 
    	//An InstantCommand is just shorthand for returning true in isFinished, meaning execute will only run once.
    	Robot.WINCHPID.disable();
    	Robot.ELEVATORPID.disable();
    }
}
