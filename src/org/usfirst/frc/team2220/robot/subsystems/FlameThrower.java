package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlameThrower extends Subsystem
{
	private static FlameThrower instance_ = new FlameThrower();

	private static CANTalon metererer, shooter;
	
	public static FlameThrower getInstance()
	{
		return instance_;
	}
	
	public FlameThrower()
	{
		shooter = new CANTalon(RobotMap.SHOOTER);
		metererer = new CANTalon(RobotMap.METERERER);
	}

	public void runShooter(double pow)
	{
		shooter.set(pow);
	}

	public void stopShooter()
	{
		shooter.set(0);
	}

	public void runMeter(double pow)
	{
		metererer.set(pow);
	}

	public void stopMeter()
	{
		metererer.set(0);
	}

	@Override
	protected void initDefaultCommand()
	{

	}
}
