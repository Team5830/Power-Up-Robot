package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTeleop extends Command {

    public DriveTeleop() {
    	requires(Robot.swerveDrive);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Status", "Operator-Controlled Driving");
    	Robot.swerveDrive.drive(Robot.driveX, Robot.driveY, Robot.rotX);
    	
    	//CALIBRATION ONLY
    	//Robot.swerveDrive.drive(0, Robot.driveY, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
}
