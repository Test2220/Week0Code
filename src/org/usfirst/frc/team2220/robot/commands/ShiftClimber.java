package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftClimber extends InstantCommand
{

	boolean shiftVal;

	public ShiftClimber(boolean shiftVal)
	{
		super();
		requires(Climber.getInstance());
		this.shiftVal = shiftVal;
	}

	// Called once when the command executes
	protected void initialize()
	{
		Climber.getInstance().shift(shiftVal);
	}
}
