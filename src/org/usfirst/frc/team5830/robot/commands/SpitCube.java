package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class SpitCube extends InstantCommand {

    public SpitCube() {
    	requires(Robot.POWERCUBE);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Spitting Cube");
    	SmartDashboard.putString("Autonomous Status", "Spitting Cube");
    	Robot.POWERCUBE.out();
    	Timer.delay(1);
    	Robot.POWERCUBE.stop();
    }

    protected void end() {
    	SmartDashboard.putString("Autonomous Status", "END");
    }

    protected void interrupted() {
    	Robot.POWERCUBE.stop();
    }
}
