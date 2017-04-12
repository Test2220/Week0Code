package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.GearIntake;
import org.usfirst.frc.team2220.robot.triggers.XBoxTrigger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearIntakeJoystick extends Command
{

	private XBoxTrigger trigger;
	public GearIntakeJoystick(XBoxTrigger trigger)
	{
		this.trigger = trigger;
		requires(GearIntake.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		GearIntake.getInstance().setMotors(trigger.getVal());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		GearIntake.getInstance().setMotors(0.0);
	}
}
