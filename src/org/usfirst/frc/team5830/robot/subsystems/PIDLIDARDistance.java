package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * This code is
 *   _   _    ___      _        ___    _   _    ____   _____   ____      _   _   ____    _____   ____  
 *	| \ | |  / _ \    | |      / _ \  | \ | |  / ___| | ____| |  _ \    | | | | / ___|  | ____| |  _ \ 
 *	|  \| | | | | |   | |     | | | | |  \| | | |  _  |  _|   | |_) |   | | | | \___ \  |  _|   | | | |
 *	| |\  | | |_| |   | |___  | |_| | | |\  | | |_| | | |___  |  _ <    | |_| |  ___) | | |___  | |_| |
 *	|_| \_|  \___/    |_____|  \___/  |_| \_|  \____| |_____| |_| \_\    \___/  |____/  |_____| |____/ 
 *                               
 * but is kept here just in case we use it again. LIDAR is replaced with a drive wheel encoder used in PIDWheelDistance subsystem.
 */
public class PIDLIDARDistance extends PIDSubsystem {

	//public double pidOutput;

	// Initialize your subsystem here
    public PIDLIDARDistance() {
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
