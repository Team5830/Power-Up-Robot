package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter P.
 *
 */
public class AutoMoveR_RSwitch extends CommandGroup {

    public AutoMoveR_RSwitch() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to switch (X defined in Robot.Java), 
    	 * rotates 90deg counterclockwise, raises cube, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new DriveStraight(Robot.distanceWallToSwitch));
    	addSequential(new DriveRotationSetpoint(-90));
    	addSequential(new CubeToSwitch());
    	addSequential(new DriveCloserToSwitch());
    	addSequential(new SpitCube());
    }
}
