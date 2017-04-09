package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueShoot extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoBlueShoot()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoShoot(8));
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(15.5), RobotMap.inchesToEncRot(-15.5), driveAccel, driveSpeed), 2);
		addSequential(new AutoMotion(36.5, 36.5, driveAccel, driveSpeed), 5);
	}
}
