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
		addSequential(new ShiftClimber(true)); // down TODO figure out if right
		addSequential(new RunGearIntake(1.0), 2); //TODO figure out which way is reverse
		addSequential(new RunGearIntake(0.0), 2);
	}
}
