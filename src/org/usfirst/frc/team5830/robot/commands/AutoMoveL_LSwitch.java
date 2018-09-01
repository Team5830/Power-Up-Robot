package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter P.
 *
 */
public class AutoMoveL_LSwitch extends CommandGroup {

    public AutoMoveL_LSwitch() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to switch (X defined in Robot.Java), 
    	 * rotates 90deg clockwise, raises cube, spits cube
    	 */
    	/*addParallel(new WinchRelease());
    	addSequential(new DriveToSwitch());
    	addSequential(new DriveRotateTo90());
    	addSequential(new CubeToSwitch());
    	addSequential(new SpitCube());*/
    	
    	addParallel(new WinchRelease());
    	addSequential(new DriveEnsureStraight());
    	addSequential(new DriveStraight(Robot.distanceWallToSwitch));
    	addSequential(new DriveRotationSetpoint(90));
    	addSequential(new CubeToSwitch());
    	addSequential(new DriveCloserToSwitch());
    	addSequential(new SpitCube());
    }
}
