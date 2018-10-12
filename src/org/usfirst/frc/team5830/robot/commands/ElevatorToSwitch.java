package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class ElevatorToSwitch extends Command {

    public ElevatorToSwitch() {
    	requires(Robot.ELEVATORPID);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Cube to Switch");
    	Robot.ELEVATORPID.setSetpoint(3000);
    	Robot.ELEVATORPID.enable();
    }

    protected boolean isFinished() {
        return Math.abs(Robot.ELEVATORPID.getSetpoint() - Robot.ELEVATORPID.getPosition()) < 500;
    }

    protected void end() {
    	SmartDashboard.putString("Status", "Teleop Driving");
    }
}
