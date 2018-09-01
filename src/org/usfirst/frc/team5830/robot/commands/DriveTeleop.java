package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class DriveTeleop extends Command {

    public DriveTeleop() {
    	requires(Robot.swerveDrive);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Operator-Controlled Driving");
    	Robot.swerveDrive.drive(Robot.driveX/2, Robot.driveY/2, Robot.rotX/2);
    	
    	//CALIBRATION ONLY
    	//Robot.swerveDrive.drive(0, Robot.driveY, 0);
    }

    protected boolean isFinished() {
    	return false;
    }
}
