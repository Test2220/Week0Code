package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedShootAndGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoRedShootAndGear()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoShoot(5));
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(114.5), RobotMap.inchesToEncRot(114.5), driveAccel,
				driveSpeed, 5));

		addSequential(new AutoReleaseGear(), 4);
	}
}
