package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftDrivetrain extends InstantCommand
{

	public boolean shiftVal = false;

	public ShiftDrivetrain(boolean shiftVal)
	{
		this.shiftVal = shiftVal;
		System.out.println("shiftVal = " + shiftVal);

	}

	// Called after the command is started... every time.
	protected void initialize()
	{
		TankDrive.getInstance().shift(shiftVal);
	}
}
