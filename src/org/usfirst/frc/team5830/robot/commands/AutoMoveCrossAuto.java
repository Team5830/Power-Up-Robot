package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter Pugh
 *
 */
public class AutoMoveCrossAuto extends CommandGroup {

    public AutoMoveCrossAuto() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to scale (X defined in Robot.Java), 
    	 * rotates 90deg clockwise, raises cube, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new DriveToAuto());
    }
}
