package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 	   _____   _____   ____    _____      ____    ___    ____    _____ 
 *	  |_   _| | ____| / ___|  |_   _|    / ___|  / _ \  |  _ \  | ____|
 * 		| |   |  _|   \___ \    | |     | |     | | | | | | | | |  _|  
 * 		| |   | |___   ___) |   | |     | |___  | |_| | | |_| | | |___ 
 * 		|_|   |_____| |____/    |_|      \____|  \___/  |____/  |_____|
 *    built for testing Motion Profiling general movement command instead of a jumble of different commands. 
 *    REQUIRES ENCODER ON WHEEL!                                                             
 */
public class AutoMoveR_RScale extends CommandGroup {

    public AutoMoveR_RScale() {
    	/*
    	 * Rundown: Releases winch kickstand, moves X inches to scale (X defined in Robot.Java), 
    	 * rotates 90deg counterclockwise, raises cube, spits cube
    	 */
    	SmartDashboard.putString("Autonomous Path Chosen", "Right Scale");
    	addParallel(new WinchRelease());
    	addSequential(new DriveToScale()); //MotionProfiling values = (<angle>, <distance>)
    	addSequential(new DriveRotateToNeg90());
    	addSequential(new CubeToScale());
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
