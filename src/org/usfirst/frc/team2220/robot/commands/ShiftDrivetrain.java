package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftDrivetrain extends InstantCommand {

    public ShiftDrivetrain(boolean shiftVal) 
    {
    	super();
    	RobotMap.driveInHighGear = shiftVal;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	RobotMap.drive.shift(RobotMap.driveInHighGear);
    }
}
