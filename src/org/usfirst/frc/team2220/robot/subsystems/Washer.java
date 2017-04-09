package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.WasherJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Washer extends Subsystem
{
	public static Washer instance_ = new Washer();

	public static Washer getInstance()
	{
		return instance_;
	}

	public void runWasher(double pow)
	{
		RobotMap.washer.set(pow);
	}

	public void runJoysticks()
	{
		RobotMap.washer.set(RobotMap.deadzone(OI.manipulatorStick.getRawAxis(5), 0.15) * -1);
	}

	public void stop()
	{
		RobotMap.washer.set(0);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new WasherJoystick());
	}
}
