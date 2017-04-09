package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveFromMidlineAndShoot extends CommandGroup
{

	double driveSpeed = 1500;
	double driveAccel = 1500;
	double turnSpeed = 600;
	double turnAccel = 600;
	double distance = -42.3 + RobotMap.inchesToEncRot(24);

	public AutoDriveFromMidlineAndShoot()
	{
		addSequential(new Delay(2));
		addSequential(new AutoMotion(-42.3 - RobotMap.inchesToEncRot(24), -42.3 - RobotMap.inchesToEncRot(24), driveAccel, driveSpeed), 5);
		addSequential(new AutoMotion(-3.625, 3.625, turnAccel, turnSpeed), 3); // 45
		addSequential(new AutoShoot(6));

		/* addSequential(new AutoShoot(6)); addSequential(new AutoMotion(7.25,
		 * -7.25, turnAccel, turnSpeed, 4)); addSequential(new
		 * AutoMotion(distance, distance, driveAccel, driveSpeed, 5)); */

		/* //turn to gear, line up and drive forward addSequential(new
		 * AutoMotion(-4.8, 4.8, turnAccel, turnSpeed, 5)); addSequential(new
		 * FBCameraMotion(2)); addSequential(new AutoMotion(4, 4, driveAccel,
		 * driveSpeed, 2)); //release addSequential(new ShiftCollector(false));
		 * addSequential(new Delay(3)); addSequential(new ShiftCollector(true)); */
	}
}
