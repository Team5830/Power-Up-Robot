package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoLogicMain extends InstantCommand {
	
	String gameData;
	Command autoMoveC_LSwitch = new AutoMoveC_LSwitch();
	Command autoMoveC_RSwitch = new AutoMoveC_RSwitch();
	Command autoMoveCrossAuto = new AutoMoveCrossAuto();
	Command autoMoveL_LScale = new AutoMoveL_LScale();
	Command autoMoveL_LSwitch = new AutoMoveL_LSwitch();
	Command autoMoveR_RScale = new AutoMoveR_RScale();
	Command autoMoveR_RSwitch = new AutoMoveR_RSwitch();

    public AutoLogicMain() {
    	}
    
    protected void execute() {
    	//There is no isFinished defined because this is an InstantCommand. 
    	//An InstantCommand is just shorthand for returning true in isFinished, meaning execute will only run once.
    	SmartDashboard.putString("Autonomous Status", "I got to Auto Logic Main");
    	//Gets the game data from the FMS
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	//Makes sure the game data was retrieved successfully
    	if(gameData.length() > 0) {
    		
    		switch (Robot.autoPosition.getSelected()){
    			case "Left":
    				SmartDashboard.putString("Autonomous Status", "I got to Left");
    				switch (Robot.autoChooser.getSelected()) {
    					case "Scale":
    						SmartDashboard.putString("Autonomous Status", "I got to Left and Scale");
    						switch (gameData) {
    							case "LLL":
    								SmartDashboard.putString("Autonomous Status", "I got to Left and Scale and LLL");
    								autoMoveL_LScale.start();
    								break;
    							case "RRR":
    								autoMoveCrossAuto.start();
    								break;
    							case "LRL":
    								autoMoveL_LSwitch.start();
    								break;
    							case "RLR":
    								autoMoveL_LScale.start();
    								break;
    						}
    						break;
    					case "ScaleOnly":
    						switch (gameData) {
								case "LLL":
									autoMoveL_LScale.start();
									break;
								case "RRR":
									autoMoveCrossAuto.start();
									break;
								case "LRL":
									autoMoveCrossAuto.start();
									break;
								case "RLR":
									autoMoveL_LScale.start();
									break;
    						}
    						break;
    					case "Switch":
    						switch (gameData) {
								case "LLL":
									autoMoveL_LSwitch.start();
									break;
								case "RRR":
									autoMoveCrossAuto.start();
									break;
								case "LRL":
									autoMoveL_LSwitch.start();
									break;
								case "RLR":
									autoMoveL_LScale.start();
									break;
    						}
    						break;
    					case "SwitchOnly":
    						switch (gameData) {
								case "LLL":
									autoMoveL_LSwitch.start();
									break;
								case "RRR":
									autoMoveCrossAuto.start();
									break;
								case "LRL":
									autoMoveL_LSwitch.start();
									break;
								case "RLR":
									autoMoveL_LScale.start();
									break;
    						}
    						break;
    					case "CrossAuto":
    						autoMoveCrossAuto.start();
    						break;
    						
    				}//END Left > Auto Mode switch
    				break;//Break Left case
    				
    			case "Center":
    				switch (Robot.autoChooser.getSelected()) {
						case "Scale":
							switch (gameData) {
								case "LLL":
									autoMoveC_LSwitch.start();
									break;
								case "RRR":
									autoMoveC_RSwitch.start();
									break;
								case "LRL":
									autoMoveC_LSwitch.start();
									break;
								case "RLR":
									autoMoveC_RSwitch.start();
									break;
							}
							break;
						case "ScaleOnly":
							switch (gameData) {
								case "LLL":
									autoMoveC_LSwitch.start();
									break;
								case "RRR":
									autoMoveC_RSwitch.start();
									break;
								case "LRL":
									autoMoveC_LSwitch.start();
									break;
								case "RLR":
									autoMoveC_RSwitch.start();
									break;
							}
							break;
						case "Switch":
							switch (gameData) {
								case "LLL":
									autoMoveC_LSwitch.start();
									break;
								case "RRR":
									autoMoveC_RSwitch.start();
									break;
								case "LRL":
									autoMoveC_LSwitch.start();
									break;
								case "RLR":
									autoMoveC_RSwitch.start();
									break;
							}
							break;
						case "SwitchOnly":
							switch (gameData) {
								case "LLL":
									autoMoveC_LSwitch.start();
									break;
								case "RRR":
									autoMoveC_RSwitch.start();
									break;
								case "LRL":
									autoMoveC_LSwitch.start();
									break;
								case "RLR":
									autoMoveC_RSwitch.start();
									break;
							}
							break;
						case "CrossAuto":
							autoMoveCrossAuto.start();
							break;
    				}//END Center > Auto Mode Switch
    				break;//Break Center case
    			case "Right":
    				switch (Robot.autoChooser.getSelected()) {
						case "Scale":
							switch (gameData) {
								case "LLL":
									autoMoveCrossAuto.start();
									break;
								case "RRR":
									autoMoveR_RScale.start();
									break;
								case "LRL":
									autoMoveR_RScale.start();
									break;
								case "RLR":
									autoMoveR_RSwitch.start();
									break;
							}
							break;
						case "ScaleOnly":
							switch (gameData) {
								case "LLL":
									autoMoveCrossAuto.start();
									break;
								case "RRR":
									autoMoveR_RScale.start();
									break;
								case "LRL":
									autoMoveR_RScale.start();
									break;
								case "RLR":
									autoMoveCrossAuto.start();
									break;
							}
							break;
						case "Switch":
							switch (gameData) {
								case "LLL":
									autoMoveCrossAuto.start();
									break;
								case "RRR":
									autoMoveR_RSwitch.start();
									break;
								case "LRL":
									autoMoveR_RScale.start();
									break;
								case "RLR":
									autoMoveR_RSwitch.start();
									break;
							}
							break;
						case "SwitchOnly":
							switch (gameData) {
								case "LLL":
									autoMoveCrossAuto.start();
									break;
								case "RRR":
									autoMoveR_RSwitch.start();
									break;
								case "LRL":
									autoMoveCrossAuto.start();
									break;
								case "RLR":
									autoMoveR_RSwitch.start();
									break;
							}
							break;
						case "CrossAuto":
							autoMoveCrossAuto.start();
							break;
						
				}//END Right > Auto Mode switch
				break;//Break Right case	
    		}//END Position Switch
    	}//END if gamedata.getLength > 0
    }//END execute
}//END Main Class