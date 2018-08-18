package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDWinch extends PIDSubsystem {

    // Initialize your subsystem here
    public PIDWinch() {
    	super("WinchPID", 0.001, 0.0, 0.0);
    	setOutputRange(-0.5, 0.25);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
    	return RobotMap.winchEncoder.getDistance();
    }

    protected void usePIDOutput(double output) {
    	RobotMap.winch.set(-output);
    }
}
