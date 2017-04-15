package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.Washer;
import org.usfirst.frc.team2220.robot.triggers.XBoxTrigger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WasherJoystick extends Command
{
	XBoxTrigger trigger;
	public WasherJoystick(XBoxTrigger trigger)
	{
		this.trigger = trigger;
		requires(Washer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	protected void execute()
	{
		Washer.getInstance().runWasher(trigger.getVal());
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
