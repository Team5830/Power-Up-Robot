package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter Pugh
 *
 */
public class AutoMoveC_RSwitch extends CommandGroup {

    public AutoMoveC_RSwitch() {
    	/*
    	 * Rundown: Releases winch kickstand, moves 10 inches, rotates 45deg clockwise,
    	 * moves X inches to switch (X defined in Robot.Java), raises cube, rotates 45deg counterclockwise,
    	 * moves 10 inches, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new DriveEnsureStraight());
    	addSequential(new DriveStrafeToRSwitch());
    	addSequential(new CubeToSwitch());
    	addSequential(new DriveCloserToSwitch());
    	addSequential(new SpitCube());
    }
}
