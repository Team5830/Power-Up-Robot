package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * 
 * @author Hunter Pugh
 *
 */
public class AutoMoveC_LSwitch extends CommandGroup {

    public AutoMoveC_LSwitch() {
    	addParallel(new WinchRelease());
    	addSequential(new DriveEnsureStraight());
    	addSequential(new DriveStrafeToLSwitch());
    	addSequential(new CubeToSwitch());
    	addSequential(new DriveCloserToSwitch());
    	addSequential(new SpitCube());
    }
}
