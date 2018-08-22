package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter P.
 *
 */
public class CubeToScale extends CommandGroup {

    public CubeToScale() {
    	addParallel(new ElevatorToScale());
    	addParallel(new WinchUp());
    }
}
