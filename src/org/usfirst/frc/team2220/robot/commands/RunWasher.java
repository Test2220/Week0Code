package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.Washer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunWasher extends Command
{

	double power = 0.0;

	public RunWasher(double pow)
	{
		power = pow;
		// Use requires() here to declare subsystem dependencies
		requires(Washer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Washer.getInstance().runWasher(power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Washer.getInstance().runWasher(0.0);
	}
}
