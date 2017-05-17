package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterTwoGear extends CommandGroup
{
	double driveSpeed = 1500;
	double driveAccel = 2000;
	double turnSpeed = 1200;
	double turnAccel = 1200;
	
	public AutoCenterTwoGear()
	{
		addSequential(new AutoInit(), 1);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(69.5), RobotMap.inchesToEncRot(69.5), driveAccel, driveSpeed), 3);
		addSequential(new AutoReleaseGear(), 2);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(-60), RobotMap.inchesToEncRot(-60), driveAccel, driveSpeed), 3);
		addSequential(new AutoMotion(7.5, -7.5, turnAccel, turnSpeed), 1);
		addSequential(new RunGearIntake(1.0));
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(5), RobotMap.inchesToEncRot(5), driveAccel, driveSpeed), 1);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(-5), RobotMap.inchesToEncRot(-5), driveAccel, driveSpeed), 1);
		addSequential(new AutoMotion(-7.5, 7.5, turnAccel, turnSpeed), 1);
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(60), RobotMap.inchesToEncRot(60), driveAccel, driveSpeed), 3);
		addSequential(new AutoReleaseGear(), 2);
	}
}
