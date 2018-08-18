package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLogicLSwitch extends CommandGroup {
Command commandLSwitch;
Command commandCrossAuto;
Command commandLScale;

public AutoLogicLSwitch() {
	commandLSwitch = new AutoMoveL_LSwitch();
	commandCrossAuto= new AutoMoveCrossAuto();
	commandLScale = new AutoMoveL_LScale();
}
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
    protected void execute() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
        	 if(gameData.startsWith("LL")) 
        	  {
        		 commandLSwitch.start();
        	  }
        	 else if(gameData.startsWith("RR"))
        	 {
        		 commandCrossAuto.start();
        	 }
        	  else if(gameData.startsWith("LR"))
        	  {
        		  commandLSwitch.start();
        	  } 
        	  else if(gameData.startsWith("RL")) 
        	  {
        		  commandLScale.start();
        	  }
    }
    }
}
