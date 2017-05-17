package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunClimber extends Command
{

	double runVal;

	public RunClimber(double inVal)
	{
		runVal = inVal;
		requires(Climber.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Climber.getInstance().run(runVal);
		SmartDashboard.putNumber("collectorSpeed", runVal);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Climber.getInstance().run(0.0);
	}

}
