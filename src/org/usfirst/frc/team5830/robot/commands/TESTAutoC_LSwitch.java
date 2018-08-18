package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 	   _____   _____   ____    _____      ____    ___    ____    _____ 
 *	  |_   _| | ____| / ___|  |_   _|    / ___|  / _ \  |  _ \  | ____|
 * 		| |   |  _|   \___ \    | |     | |     | | | | | | | | |  _|  
 * 		| |   | |___   ___) |   | |     | |___  | |_| | | |_| | | |___ 
 * 		|_|   |_____| |____/    |_|      \____|  \___/  |____/  |_____|
 *    built for testing Motion Profiling general movement command instead of a jumble of different commands. 
 *    REQUIRES ENCODER ON WHEEL!                                                             
 */
public class TESTAutoC_LSwitch extends CommandGroup {

    public TESTAutoC_LSwitch() {
    	/*
    	 * Rundown: Releases winch kickstand, moves 10 inches, rotates 45deg counterclockwise,
    	 * moves X inches to switch (X defined in Robot.Java), raises cube, rotates 45deg clockwise,
    	 * moves 10 inches, spits cube
    	 */
    	addParallel(new WinchRelease());
    	addSequential(new MotionProfiling(0, 10));
    	addSequential(new MotionProfiling(-45, Robot.distanceCWallToSwitch));
    	addSequential(new CubeToSwitch());
    	addSequential(new MotionProfiling(0, 10));
    	addSequential(new SpitCube());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}