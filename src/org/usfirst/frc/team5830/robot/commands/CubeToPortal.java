package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CubeToPortal extends CommandGroup {

    public CubeToPortal() {
    	addParallel(new ElevatorToPortal());
    	addParallel(new WinchDown());
    }
}
