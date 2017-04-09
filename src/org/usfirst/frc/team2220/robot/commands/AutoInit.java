package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Always use timeout based on what amount of time you want spent on building
 * air pressure / waiting around
 */
public class AutoInit extends CommandGroup
{

	public AutoInit()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new ShiftClimber(true)); //figure out what is up for gear slot
		addSequential(new Delay(1000));
	}
}
