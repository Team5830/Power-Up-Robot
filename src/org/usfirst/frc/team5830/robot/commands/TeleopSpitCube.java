package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopSpitCube extends Command {

    public TeleopSpitCube() {
    	requires(Robot.POWERCUBE);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Status", "Spitting Cube");
    	if(Robot.lidarSubsystem.getDistanceIn(false) <= Robot.cubeDistance) Robot.POWERCUBE.out();
    	else if(SmartDashboard.getBoolean("Override Intake Sensor", false)) Robot.POWERCUBE.out();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.POWERCUBE.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.POWERCUBE.stop();
    }
}
