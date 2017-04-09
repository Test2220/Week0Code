package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueHopper extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;
	double distance = 38; // 9.5 feet

	public AutoBlueHopper()
	{

		addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed), 5); // 9.5 feet foward
		addSequential(new AutoMotion(7.25, -7.25, turnAccel, turnSpeed), 4); // 90 degrees right
		addSequential(new AutoMotion(-17.3, -17.3, driveAccel, driveSpeed), 5); // 4 ft 4 inches backwards
		addSequential(new Delay(1));
		addSequential(new AutoMotion(-7.25, 7.25, turnAccel, turnSpeed), 4); // 90 degrees left

		addSequential(new AutoMotion(-20.667, -20.667, driveAccel, driveSpeed), 5); // 5ft 2 inch backwards
		addSequential(new AutoShoot(10)); // shoot

	}
}
