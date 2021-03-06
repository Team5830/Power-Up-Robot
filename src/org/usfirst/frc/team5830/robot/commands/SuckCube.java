package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SuckCube extends Command {

	private Command commandWinchUp = new WinchUp();

    public SuckCube() {
    	requires(Robot.POWERCUBE);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Grabbing Cube");
    	//Only sucks cube if cube isn't there, UNLESS override is enabled in Shuffleboard
    	if(!SmartDashboard.getBoolean("Override Intake Sensor", false)) { //If sensor override is NOT enabled
    		if(Robot.lidarSubsystem.getDistanceIn(false) >= Robot.cubeDistance) Robot.POWERCUBE.in(); //If distance to cube is more than threshold, suck cube
    		else {
    			Timer.delay(0.3);
    			commandWinchUp.start(); //If distance to cube is SHORTER than threshold, raise the winch to get the cube off the ground
    			Robot.POWERCUBE.stop();
    		}
    	}
    	else Robot.POWERCUBE.in(); //If override button is enabled, just suck the cube no matter what
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	SmartDashboard.putString("Status", "Teleop Driving");
    	Robot.POWERCUBE.stop();
    }

    protected void interrupted() {
    	SmartDashboard.putString("Status", "Teleop Driving");
    	Robot.POWERCUBE.stop();
    }
}
