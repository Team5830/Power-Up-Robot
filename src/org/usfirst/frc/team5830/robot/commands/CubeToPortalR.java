package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P.
 *
 */
public class CubeToPortalR extends InstantCommand {
	
	private Command localCubeToPortal = new CubeToPortal();

    public CubeToPortalR() {}

    protected void execute() {
    	localCubeToPortal.start();
    	//Robot.rotateTo45.start();
    }
}
