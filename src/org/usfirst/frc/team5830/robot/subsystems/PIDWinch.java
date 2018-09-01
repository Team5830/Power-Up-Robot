package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PIDWinch extends PIDSubsystem {

    public PIDWinch() {
    	super("WinchPID", 0.001, 0.0, 0.0);
    	setOutputRange(-0.6, 0.25);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
    	return RobotMap.winchEncoder.getDistance();
    }

    protected void usePIDOutput(double output) {
    	RobotMap.winch.set(output);
    }
}
