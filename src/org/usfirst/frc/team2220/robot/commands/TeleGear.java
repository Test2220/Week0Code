package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleGear extends CommandGroup
{
	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	double distance = 8;

	public TeleGear()
	{
		addSequential(new FBCameraMotion(2));
		addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed), 2);

		// release gear
		addSequential(new ShiftClimber(false));
		addSequential(new Delay(3));
		addSequential(new ShiftClimber(true));
	}
}
