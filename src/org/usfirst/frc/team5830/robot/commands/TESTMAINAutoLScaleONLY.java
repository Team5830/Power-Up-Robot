package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TESTMAINAutoLScaleONLY extends CommandGroup {
	
	Command commandLScale;
	Command commandLSwitch;
	Command commandCrossAuto;

    public TESTMAINAutoLScaleONLY() {
    	commandLScale = new TESTAutoL_LScale();
    	commandLSwitch = new TESTAutoL_LSwitch();
    	commandCrossAuto = new TESTCrossAuto();
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
        		 commandLScale.start();
        	  }
        	 else if(gameData.startsWith("RR"))
        	 {
        		commandCrossAuto.start();
        	 }
        	  else if(gameData.startsWith("LR"))
        	  {
        		  commandCrossAuto.start();
        	  } 
        	  else if(gameData.startsWith("RL")) 
        	  {
        		 commandLScale.start();
        	  }
    }
}
}