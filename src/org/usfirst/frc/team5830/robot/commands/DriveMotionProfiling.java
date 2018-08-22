package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 * 
 */

/**
 * How to use this command for autonomous:
 * Call command like:
 * 		new MotionProfiling(<angle>, <distance>);
 * <angle> refers to the absolute angle setpoint in degrees, a value between -180 and 180 with 0 facing directly away from the alliance wall.
 * <distance> refers to the amount in inches the robot will move after rotating to the <angle>.
 */
public class DriveMotionProfiling extends Command {
	
	public static double driveMotionProfilingDistanceTolerance = 2;
	
	private int loopCount = 0;
	public static double driveAngle;
	public static double driveDistance;

	//Negative angle numbers allowed. Negative distance numbers NOT ALLOWED. Instead, have the robot rotate away then move forward.
	
	//I (Hunter P.) would have LOVED to make the variables set directly when command is called, but CommandGroups don't support
	//two of the same command but with different variables. Stupid. Now they have to be set separately in another command.
    public DriveMotionProfiling() {
    	super ("DriveMotionProfiling");
    	
    	//requires(Robot.WHEELDISTANCEPID);
    	requires(Robot.swerveDrive);
    	//localAngle = angle;
    	//driveMotionProfilingDistance = distance;
    }

    protected void initialize() {
    	SmartDashboard.putString("Status", "Automatically Driving");
    }

    protected void execute() {
    	SmartDashboard.putString("Auto Rotation Angle", "All the way!");
    	
    	//If the robot isn't at the angle it's supposed to be at, it will wait until the gyro PID finishes correcting it.
    	if(Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) > 5) {//TODO set tolerance (currently 5)
    		SmartDashboard.putString("Autonomous Status", "I got to MotionProfiling Stage 1");
    		//Gets the angle requested by the command start variable and sets the PID loop to it, then enables it
        	Robot.auto_GYRO_Correction_Swerve.setSetpoint(driveAngle);
        	Robot.auto_GYRO_Correction_Swerve.enable();
        	Robot.swerveDrive.drive(0, 0, Robot.pidOutputAngle);
        	SmartDashboard.putNumber("AutoGyroNumber", Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()));
    	} else {	
        	SmartDashboard.putString("Autonomous Status", "I got to MotionProfiling Stage 2");
        	//Counts the amount of loops this has gone through
        	loopCount = Math.abs(loopCount + 1);//make sure this doesn't crash the code with an infinite loop
        	
    		//Only resets the encoder if this is the first time running the loop
    		//Cannot place in initialize() because it needs to reset once only after gyro PID has completed its rotation
        	if (loopCount <= 1) RobotMap.wheelEncoder1.reset();
    		
        	SmartDashboard.putNumber("LocalDistance", driveDistance);
        	if(RobotMap.wheelEncoder1.getDistance() < driveDistance) {
        		Robot.swerveDrive.drive(0, 0.5, 0);
        		SmartDashboard.putString("Autonomous Status", "I got to Driving");
        	} else { 
        		Robot.swerveDrive.drive(0, 0, 0);
        		SmartDashboard.putString("Autonomous Status", "I got to Stop Driving");
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Only is finished when both distance and angle setpoints have been met
        return Math.abs(RobotMap.wheelEncoder1.getDistance() - driveDistance) < driveMotionProfilingDistanceTolerance && Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) < 5 && Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) > (0 - 3);
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Resets loop counter to 0
    	loopCount = 0;
    	
    	SmartDashboard.putString("Autonomous Status", "I got to End MotionProfiling");
    	
    	//Disables all associated PID loops
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    	//Robot.WHEELDISTANCEPID.disable();
    }
}
