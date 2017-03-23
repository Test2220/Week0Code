package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	double distance = 37;

	public AutoLeftGear()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed, 5));
		addSequential(new AutoMotion(-4.8, 4.8, turnAccel, turnSpeed, 5)); // 60
																			// degrees
		// addSequential(new FBCameraMotion(2));
		addSequential(new AutoMotion(4, 4, driveAccel, driveSpeed, 2));

		// release gear
		addSequential(new ShiftCollector(true));
		addSequential(new Delay(2));
		addSequential(new AutoMotion(-4, -4, driveAccel, driveSpeed, 5));
		addSequential(new ShiftCollector(false));
	}
}
