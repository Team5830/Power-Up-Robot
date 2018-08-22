package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter P.
 *
 */
public class CubeToPortalL extends InstantCommand {
	
	private Command localCubeToPortal = new CubeToPortal();

    public CubeToPortalL() {}

    protected void execute() {
    	localCubeToPortal.start();
    	Robot.rotateToNeg45.start();
    }
}
