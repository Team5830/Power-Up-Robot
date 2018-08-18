package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class WheelDistancePID extends PIDSubsystem {

    // Initialize your subsystem here
    public WheelDistancePID() {
    	super("WheelDistancePID", 0.5, 0.0, 0.0);
    	setOutputRange(-0.5, 0.5);
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
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return RobotMap.wheelEncoder1.getDistance();
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputWheel = output;
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
