package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueHopper extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;
	double distance = 38; // 9.5 feet

	public AutoBlueHopper()
	{

		addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed, 5)); // 9.5
																						// feet
																						// foward
		addSequential(new AutoMotion(7.25, -7.25, turnAccel, turnSpeed, 4)); // 90
																				// degrees
																				// right
		addSequential(new AutoMotion(-17.3, -17.3, driveAccel, driveSpeed, 5)); // 4
																				// ft
																				// 4
																				// inches
																				// backwards
		addSequential(new Delay(1));
		addSequential(new AutoMotion(-7.25, 7.25, turnAccel, turnSpeed, 4)); // 90
																				// degrees
																				// left

		addSequential(new AutoMotion(-20.667, -20.667, driveAccel, driveSpeed, 5)); // 5ft
																					// 2
																					// inch
																					// backwards
		addSequential(new AutoShoot(10)); // shoot

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
