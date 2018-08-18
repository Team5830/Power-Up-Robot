package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * WARNING! This code is
 * 	 ____    _____   ____    ____    _____    ____      _      _____   _____   ____  
 *	|  _ \  | ____| |  _ \  |  _ \  | ____|  / ___|    / \    |_   _| | ____| |  _ \ 
 *	| | | | |  _|   | |_) | | |_) | |  _|   | |       / _ \     | |   |  _|   | | | |
 *	| |_| | | |___  |  __/  |  _ <  | |___  | |___   / ___ \    | |   | |___  | |_| |
 *	|____/  |_____| |_|     |_| \_\ |_____|  \____| /_/   \_\   |_|   |_____| |____/ 
 *                                                                                                                                                      
 * and must be revised before use.
 *
 */

public class CDriveToSwitchL extends Command {

    public CDriveToSwitchL() {
    	requires(Robot.auto_LIDAR_Distance_Swerve);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Autonomous Status", "Driving to L Switch");
    	Robot.auto_LIDAR_Distance_Swerve.setSetpoint(Robot.distanceWallToSwitch);
    	Robot.auto_LIDAR_Distance_Swerve.enable();
    	Robot.swerveDrive.drive(-0.2, Robot.pidOutputRobot, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.auto_LIDAR_Distance_Swerve.getSetpoint() - Robot.auto_LIDAR_Distance_Swerve.getPosition()) < 1; //Ends command when robot is 1 inch away from setpoint
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous Status", "END");
    	Robot.auto_LIDAR_Distance_Swerve.disable();
    	Robot.swerveDrive.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
