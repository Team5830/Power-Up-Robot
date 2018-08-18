package org.usfirst.frc.team5830.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckMotionProfilingFinished extends Command {

    public CheckMotionProfilingFinished() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DriveMotionProfiling.driveMotionProfilingDistance < DriveMotionProfiling.driveMotionProfilingDistanceTolerance;
    }
}
