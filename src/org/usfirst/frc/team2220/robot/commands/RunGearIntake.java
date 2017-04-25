package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunGearIntake extends Command
{

	double motorVal;

	public RunGearIntake(double inputVal)
	{
		requires(GearIntake.getInstance());
		motorVal = inputVal;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		GearIntake.getInstance().setMotors(motorVal);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
