package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SpitCube extends InstantCommand {

    public SpitCube() {
    	requires(Robot.POWERCUBE);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Status", "Spitting Cube");
    	SmartDashboard.putString("Autonomous Status", "Spitting Cube");
    	Robot.POWERCUBE.out();
    	Timer.delay(1);
    	Robot.POWERCUBE.stop();
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous Status", "END");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.POWERCUBE.stop();
    }
}
