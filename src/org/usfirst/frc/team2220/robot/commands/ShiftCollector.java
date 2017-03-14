package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftCollector extends InstantCommand {

	public ShiftCollector(boolean shiftVal) 
	{
        super();
        requires(RobotMap.intake);
        RobotMap.collectorInHighGear = shiftVal;
    }

    // Called once when the command executes
    protected void initialize() 
    {
    	RobotMap.intake.shift(RobotMap.collectorInHighGear);
    }
}
