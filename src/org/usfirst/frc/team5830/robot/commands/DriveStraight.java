package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveStraight extends Command {
	
	private double driveStraightP = 0.03;
	private double driveStraightI = 0.0;
	private double driveStraightD = 0.0;
	
	private double driveDistance;
	private double gyroLockValue;
	public static boolean isItFinished = false;
	
    public DriveStraight(double distance) {
        requires(Robot.swerveDrive);
        requires(Robot.pidROTATIONCORRECTION);
    	driveDistance = distance;
    }

    protected void initialize() {
    	isItFinished = false;
    	//Sets the current gyroscope value to the locking point, so the robot will drive straight from its starting position
    	gyroLockValue = Robot.GYROSUBSYSTEM.getGyroClampedNeg180To180();
    	
    	//Sets the driveStraight PID loop to the proper gains for straight protection, sets setpoint to current angle, 
    	Robot.pidROTATIONCORRECTION.getPIDController().setP(driveStraightP);
    	Robot.pidROTATIONCORRECTION.getPIDController().setI(driveStraightI);
    	Robot.pidROTATIONCORRECTION.getPIDController().setD(driveStraightD);
    	Robot.pidROTATIONCORRECTION.setOutputRange(-0.05, 0.05);
    	Robot.pidROTATIONCORRECTION.setSetpoint(gyroLockValue);
    	Robot.pidROTATIONCORRECTION.enable();
    	
    	///Resets wheel distance encoder to 0
    	RobotMap.wheelEncoder1.reset();
    }

    protected void execute() {
    	if(RobotMap.wheelEncoder1.getDistance() < driveDistance) {
    		Robot.swerveDrive.drive(0, -0.35, Robot.pidOutputAngle);
    		
    	} else { 
    		Robot.swerveDrive.drive(0, 0, 0);
    		isItFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isItFinished;
    }

    protected void end() {
    	Robot.pidROTATIONCORRECTION.disable();
    }

    protected void interrupted() {
    	Robot.pidROTATIONCORRECTION.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    	isItFinished = false;
    }
}
