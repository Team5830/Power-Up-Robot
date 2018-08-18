package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GyroSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private double gyroAngleClamped;
	private double gyroAngleClampedShifted;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Converts the absolute gyro value to one between -180 and 180
    //Divides the gyro angle by 360 and uses the remainder as the return value
    public double getGyroClampedNeg180To180() {
    	return Math.IEEEremainder(RobotMap.gyro.getAngle(), 360);
    }
    
    //Converts the absolute gyro value to one between 0 and 360
    //Divides the gyro angle by 360. If remainder is less than 0, add 360, otherwise leave it alone. Return that value.
    public double getGyroClamped0To360() {
    	
    	gyroAngleClamped = Math.IEEEremainder(RobotMap.gyro.getAngle(), 360);
    	
    	if(gyroAngleClamped < 0) {
    		gyroAngleClampedShifted = Math.abs(gyroAngleClamped + 360);
    	} else {
    		gyroAngleClampedShifted = gyroAngleClamped;
    	}
    	return gyroAngleClampedShifted;
    }
}

