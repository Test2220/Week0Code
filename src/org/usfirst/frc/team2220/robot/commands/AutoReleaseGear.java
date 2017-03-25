package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoReleaseGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;

	public AutoReleaseGear()
	{
		addSequential(new ShiftCollector(true)); // up
		// addSequential(new Delay(2));
		addSequential(new AutoMotion(-4, -4, driveAccel, driveSpeed, 2));
		addSequential(new ShiftCollector(false)); // down

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}