package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class AUTO_GYRO_Correction_Swerve extends PIDSubsystem {

	//public double pidOutput;

	// Initialize your subsystem here
    public AUTO_GYRO_Correction_Swerve() {
    	super("AUTO_GYRO_Correction_Swerve", 0.1, 0.0, 0.0);
    	setInputRange(-180, 180);
    	getPIDController().setContinuous();
    	//setAbsoluteTolerance(2);
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
    	//Uses clamped gyro values to stop robot from spinning around
        return Robot.GYROSUBSYSTEM.getGyroClampedNeg180To180();
		
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputAngle = output;
    }
}
