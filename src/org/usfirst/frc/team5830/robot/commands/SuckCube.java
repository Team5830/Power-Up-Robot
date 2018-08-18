package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SuckCube extends Command {

	private Command commandWinchUp = new WinchUp();

    public SuckCube() {
    	requires(Robot.POWERCUBE);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Status", "Grabbing Cube");
    	//Only sucks cube if cube isn't there, UNLESS override is enabled in Shuffleboard
    	if(!SmartDashboard.getBoolean("Override Intake Sensor", false)) { //If sensor override is NOT enabled
    		if(Robot.lidarSubsystem.getDistanceIn(false) >= Robot.cubeDistance) Robot.POWERCUBE.in(); //If distance to cube is more than threshold, suck cube
    		else commandWinchUp.start(); //If distance to cube is SHORTER than threshold, raise the winch to get the cube off the ground
    	}
    	else Robot.POWERCUBE.in(); //If override button is enabled, just suck the cube no matter what
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
