package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter Pugh
 *
 */
public class AutoMoveL_LScale extends CommandGroup {

    public AutoMoveL_LScale() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to scale (X defined in Robot.Java), 
    	 * rotates 90deg clockwise, raises cube, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new DriveEnsureStraight());
    	addSequential(new DriveStraight(Robot.distanceWallToScale));
    	addSequential(new DriveRotationSetpoint(90));
    	addSequential(new CubeToScale());
    	addSequential(new SpitCube());
    }
}
