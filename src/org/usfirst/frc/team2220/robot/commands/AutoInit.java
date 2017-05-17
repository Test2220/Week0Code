package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Always use timeout based on what amount of time you want spent on building
 * air pressure / waiting around (max 5 seconds)
 */
public class AutoInit extends CommandGroup
{

	public AutoInit()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new ShiftClimber(false)); //FALSE IS UP
		addSequential(new Delay(5)); //default to five seconds
	}
}
