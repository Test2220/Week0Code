package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.FlameThrower;
import org.usfirst.frc.team2220.robot.subsystems.Washer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoShoot extends TimedCommand
{

	double meterPower = -1.0;
	double shooterPower = 0.8;
	Timer startTimer;

	public AutoShoot(double timeout)
	{
		super(timeout);
		requires(FlameThrower.getInstance());
		requires(Washer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		startTimer = new Timer();
		startTimer.reset();
		startTimer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		FlameThrower.getInstance().runShooter(shooterPower);
		if (startTimer.get() > 0.5) // arbitrary
		{
			FlameThrower.getInstance().runMeter(meterPower);
			Washer.getInstance().runWasher(1.0);
		}
		else
		{
			FlameThrower.getInstance().stopMeter();
			Washer.getInstance().runWasher(0.0);
		}
	}

	// Called once after timeout
	protected void end()
	{
		FlameThrower.getInstance().stopMeter();
		FlameThrower.getInstance().stopShooter();
		Washer.getInstance().runWasher(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
