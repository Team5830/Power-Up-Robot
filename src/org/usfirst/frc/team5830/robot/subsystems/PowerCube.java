package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class PowerCube extends Subsystem {
	
	public void in() {
		RobotMap.powerCube.set(0.6);
	}

	public void out() {
		RobotMap.powerCube.set(-0.75);
	}
	
	public void stop() {
		RobotMap.powerCube.set(0);
	}
	
    public void initDefaultCommand() {}
}

