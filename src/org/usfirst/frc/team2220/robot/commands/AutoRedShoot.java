package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedShoot extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoRedShoot()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoShoot(8));
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(-7.25), RobotMap.inchesToEncRot(7.25), driveAccel,
				driveSpeed, 2));
		addSequential(new AutoMotion(36.5, 36.5, driveAccel, driveSpeed, 5));
	}
}
