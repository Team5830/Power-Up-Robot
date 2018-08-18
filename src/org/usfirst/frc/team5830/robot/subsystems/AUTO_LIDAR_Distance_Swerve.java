package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class AUTO_LIDAR_Distance_Swerve extends PIDSubsystem {

	//public double pidOutput;

	// Initialize your subsystem here
    public AUTO_LIDAR_Distance_Swerve() {
    	super("AUTO_LIDAR_Distance_Swerve", 0.05, 0.0, 0.0);
    	setAbsoluteTolerance(5);
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
        return Robot.lidarSubsystem.getDistanceIn(true);
		// Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
		
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputRobot = output;
    	// Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}