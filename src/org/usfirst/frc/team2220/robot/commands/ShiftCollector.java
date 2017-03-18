package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftCollector extends InstantCommand {

	boolean shiftVal;
	public ShiftCollector(boolean shiftVal) 
	{
        super();
        requires(RobotMap.intake);
        this.shiftVal = shiftVal;
    }

    // Called once when the command executes
    protected void initialize() 
    {
    	RobotMap.collectorInHighGear = shiftVal;
    	RobotMap.intake.shift(shiftVal);
    }
}
