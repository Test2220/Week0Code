package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBaseline extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;
	// 8 rotations = 2 feet
	double distance = 36.5;

	public AutoBaseline()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed, 5));
	}
}
