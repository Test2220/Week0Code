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
		addSequential(new ShiftClimber(true)); // up
		// addSequential(new Delay(2));
		addSequential(new AutoMotion(-4, -4, driveAccel, driveSpeed), 2);
		addSequential(new ShiftClimber(false)); // down
	}
}
