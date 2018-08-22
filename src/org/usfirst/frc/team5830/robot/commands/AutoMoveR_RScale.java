package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter P.
 *
 */
public class AutoMoveR_RScale extends CommandGroup {

    public AutoMoveR_RScale() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to scale (X defined in Robot.Java), 
    	 * rotates 90deg counterclockwise, raises cube, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new DriveToScale());
    	addSequential(new DriveRotateToNeg90());
    	addSequential(new CubeToScale());
    	addSequential(new SpitCube());
    }
}
