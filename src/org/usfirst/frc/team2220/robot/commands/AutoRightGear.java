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
	double turnSpeed = 800;
	double turnAccel = 800;

	public AutoRightGear()
	{
		addSequential(new AutoInit(), 2);
		//88
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(83), RobotMap.inchesToEncRot(83), driveAccel, driveSpeed), 5);
		addSequential(new AutoMotion(4.65, -4.65, turnAccel, turnSpeed), 3); // 60 degrees CCW
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(47), RobotMap.inchesToEncRot(47), driveAccel, driveSpeed), 5);
		
		addSequential(new AutoReleaseGear(), 2);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(-8), RobotMap.inchesToEncRot(-8), driveAccel, driveSpeed), 2);
	}
}
