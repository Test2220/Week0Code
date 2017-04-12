package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Washer extends Subsystem
{
	private static Washer instance_ = new Washer();

	public static Washer getInstance()
	{
		return instance_;
	}

	public void runWasher(double pow)
	{
		RobotMap.washer.set(pow);
	}


	public void stop()
	{
		RobotMap.washer.set(0);
	}

	public void initDefaultCommand()
	{
	}
}
