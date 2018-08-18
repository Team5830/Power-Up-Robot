package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;
import org.usfirst.frc.team5830.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Hunter Pugh
 * 
 */

/**
 * How to use this command for autonomous:
 * Call command like:
 * 		new MotionProfiling(<angle>, <distance>);
 * <angle> refers to the absolute angle setpoint in degrees, a value between -180 and 180 with 0 facing directly away from the alliance wall.
 * <distance> refers to the amount in inches the robot will move after rotating to the <angle>.
 */
public class MotionProfiling extends Command {
	
	private int loopCount = 0;
	private double localAngle;
	private double localDistance;

    public MotionProfiling(double angle, double distance) {//TODO Make sure variables get passed properly
    	requires(Robot.WHEELDISTANCEPID);
    	angle = localAngle;
    	distance = localDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//Gets the angle requested by the command start variable and sets the PID loop to it, then enables it
    	Robot.auto_GYRO_Correction_Swerve.setSetpoint(localAngle);
    	Robot.auto_GYRO_Correction_Swerve.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Counts the amount of loops this has gone through
    	loopCount = Math.abs(loopCount + 1);//make sure this doesn't crash the code with an infinite loop
    	
    	
    	//If the robot isn't at the angle it's supposed to be at, it will wait until the gyro PID finishes correcting it.
    	if(Math.abs(Robot.auto_GYRO_Correction_Swerve.getSetpoint() - Robot.auto_GYRO_Correction_Swerve.getPosition()) < 5) {//TODO set tolerance (currently 5)
    		Robot.WHEELDISTANCEPID.setSetpoint(localDistance);
    		Robot.WHEELDISTANCEPID.enable();
        	//Only resets the encoder if this is the first time running the loop
    		//Cannot place in initialize() because it needs to reset once only after gyro PID has completed its rotation
        	if (loopCount < 1) {
        		RobotMap.wheelEncoder1.reset();
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.WHEELDISTANCEPID.getSetpoint() - Robot.WHEELDISTANCEPID.getPosition()) < 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Resets loop counter to 0
    	loopCount = 0;
    	
    	//Disables all associated PID loops
    	Robot.auto_GYRO_Correction_Swerve.disable();
    	Robot.WHEELDISTANCEPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
