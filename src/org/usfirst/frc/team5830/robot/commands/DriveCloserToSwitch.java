package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter Pugh
 *
 */
public class DriveCloserToSwitch extends InstantCommand {

    public DriveCloserToSwitch() {
        requires(Robot.swerveDrive);
    }
    
    protected void execute() {
    	Robot.swerveDrive.drive(0, 0.25, 0);
    	Timer.delay(2);
    	Robot.swerveDrive.drive(0, 0, 0);
    }
}
