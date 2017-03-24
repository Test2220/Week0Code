package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGear extends CommandGroup
{

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;
	// double distance = 34.7; // 34.7???

	public AutoCenterGear()
	{
		addSequential(new ShiftDrivetrain(false));
		addSequential(new AutoMotion(RobotMap.inchesToEncRot(67.5), RobotMap.inchesToEncRot(67.5), driveAccel,
				driveSpeed, 5));

		addSequential(new AutoReleaseGear(), 4);

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
