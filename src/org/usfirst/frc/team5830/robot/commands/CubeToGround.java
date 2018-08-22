package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CubeToGround extends CommandGroup {

    public CubeToGround() {
    	addParallel(new ElevatorToGround());
    	addParallel(new WinchDown());
    }
}
