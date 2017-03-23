package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlameThrower extends Subsystem
{

	public void runShooter(double pow)
	{
		RobotMap.shooter.set(pow);
	}

	public void stopShooter()
	{
		RobotMap.shooter.set(0);
	}

	public void runMeter(double pow)
	{
		RobotMap.metererer.set(pow);
	}

	public void stopMeter()
	{
		RobotMap.metererer.set(0);
	}

	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub

	}
}
