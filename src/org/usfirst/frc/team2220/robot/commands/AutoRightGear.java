package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoRightGear()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(
				new AutoMotion(RobotMap.inchesToEncRot(20), RobotMap.inchesToEncRot(20), driveAccel, driveSpeed, 2));
		addSequential(new AutoMotion(3.625, -3.625, turnAccel, turnSpeed, 4)); // 45
																				// degrees
																				// CCW

		addSequential(new AutoMotion(RobotMap.inchesToEncRot(104.6), RobotMap.inchesToEncRot(104.6), driveAccel,
				driveSpeed, 5));

		addSequential(new AutoReleaseGear(), 4);
	}
}
