package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AccelerateClimber extends Command
{

	Timer t;
	double aTime;

	public AccelerateClimber(double accelTime)
	{
		requires(Climber.getInstance());
		aTime = accelTime;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		t = new Timer();
		t.reset();
		t.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (t.get() < aTime)
		{
			Climber.getInstance().run((t.get() / aTime) * 1.0);
		}
		else
		{
			Climber.getInstance().run(1.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Climber.getInstance().run(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		Climber.getInstance().run(0);
	}
}
