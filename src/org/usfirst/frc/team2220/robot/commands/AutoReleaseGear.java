package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoReleaseGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoReleaseGear()
	{
		addSequential(new ShiftClimber(true)); // TRUE IS DOWN
		addSequential(new RunGearIntake(1.0), 1); //NEGATIVE IS REVERSE
		addSequential(new RunGearIntake(0.0), 0.5);
	}
}
