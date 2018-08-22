package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Hunter P.
 *
 */
public class CubeToSwitch extends CommandGroup {

    public CubeToSwitch() {
    	addParallel(new ElevatorToSwitch());
    	addParallel(new WinchUp());
    }
}
