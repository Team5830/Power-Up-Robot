package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Hunter P.
 *
 */
public class TeleopSpitCube extends Command {

    public TeleopSpitCube() {
    	requires(Robot.POWERCUBE);
    }

    protected void execute() {
    	SmartDashboard.putString("Status", "Spitting Cube");
    	if(Robot.lidarSubsystem.getDistanceIn(false) <= Robot.cubeDistance) Robot.POWERCUBE.out();
    	else if(SmartDashboard.getBoolean("Override Intake Sensor", false)) Robot.POWERCUBE.out();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.POWERCUBE.stop();
    }

    protected void interrupted() {
    	Robot.POWERCUBE.stop();
    }
}
