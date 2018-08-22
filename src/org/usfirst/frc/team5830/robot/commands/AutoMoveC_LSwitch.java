package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * 
 * @author Hunter Pugh
 *
 */
public class AutoMoveC_LSwitch extends CommandGroup {

    public AutoMoveC_LSwitch() {
    	/*
    	 * Rundown: Releases winch kickstand, moves 10 inches, rotates 45deg counterclockwise,
    	 * moves X inches to switch (X defined in Robot.Java), raises cube, rotates 45deg clockwise,
    	 * moves 10 inches, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addParallel(new DriveStrafeToLSwitch());
    	addSequential(new CubeToSwitch());
    	addSequential(new DriveCloserToSwitch());
    	addSequential(new SpitCube());
    }
}
