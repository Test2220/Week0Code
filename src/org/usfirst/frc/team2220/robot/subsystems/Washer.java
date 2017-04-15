package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Washer extends Subsystem
{
	private static Washer instance_ = new Washer();
	private static CANTalon washer;
	
	
	public static Washer getInstance()
	{
		return instance_;
	}
	
	public Washer()
	{
		washer = new CANTalon(RobotMap.WASHER);
	}

	public void runWasher(double pow)
	{
		washer.set(pow);
	}


	public void stop()
	{
		washer.set(0);
	}

	public void initDefaultCommand()
	{
	}
}
