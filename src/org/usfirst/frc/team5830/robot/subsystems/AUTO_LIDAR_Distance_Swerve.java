package org.usfirst.frc.team5830.robot.subsystems;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 * @author Hunter P.
 *
 */
public class AUTO_LIDAR_Distance_Swerve extends PIDSubsystem {

    public AUTO_LIDAR_Distance_Swerve() {
    	super("AUTO_LIDAR_Distance_Swerve", 0.05, 0.0, 0.0);
    	setAbsoluteTolerance(5);
    	setOutputRange(-0.5, 0.5);
    }

    public void initDefaultCommand() {}

    protected double returnPIDInput() {
        return Robot.lidarSubsystem.getDistanceIn(true);
    }

    protected void usePIDOutput(double output) {
    	Robot.pidOutputRobot = output;
    }
}
