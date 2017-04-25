package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 2000;
	double turnSpeed = 600;
	double turnAccel = 600;
	// double distance = 34.7; // 34.7???

	public AutoCenterGear()
	{
		addSequential(new AutoInit(), 3);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(50), RobotMap.inchesToEncRot(50), driveAccel, driveSpeed), 5);
		addSequential(new GearCameraLineUp(), 3);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(17.5), RobotMap.inchesToEncRot(17.5), driveAccel, driveSpeed), 5);
		addSequential(new AutoReleaseGear(), 4);
	}
}
