package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.*;
import org.usfirst.frc.team2220.robot.triggers.DriveTrigger;
import lib.util.XBox;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	
	public static XBox driverStick = new XBox(0);
	public static XBox manipulatorStick = new XBox(1);

	private static DriveTrigger isDriving = new DriveTrigger();

	public static void init()
	{
		//////////
		//Driver//
		//////////
		driverStick.yButton.whenPressed(new TeleMotion(2.5, 2.5, 500, 1000));
		driverStick.leftBumper.whenPressed(new ShiftDrivetrain(true));
		driverStick.rightBumper.whenPressed(new ShiftDrivetrain(false));
		
		isDriving.whileActive(new DriveWithControllers(driverStick.rightYAxis, driverStick.leftYAxis));
		isDriving.whenInactive(new DriveOff());

		///////////////
		//Manipulator//
		///////////////
		manipulatorStick.leftYAxis.setDeadzone(0.15);
		manipulatorStick.leftYAxis.whenActive(new GearIntakeJoystick(manipulatorStick.leftYAxis));
		
		manipulatorStick.rightTrigger.setDeadzone(0.10);
		manipulatorStick.rightTrigger.whileActive(new ClimberJoystick(manipulatorStick.rightTrigger));
		
		manipulatorStick.rightYAxis.setDeadzone(0.15);
		manipulatorStick.rightYAxis.whenActive(new WasherJoystick(manipulatorStick.rightYAxis));

		manipulatorStick.leftTrigger.whileActive(new RunClimber(1.0));
		manipulatorStick.leftBumper.whileHeld(new RunWasher(1.0));
		manipulatorStick.rightBumper.whileHeld(new RunWasher(-1.0));
		manipulatorStick.aButton.whenPressed(new RootyTootyPointAndShooty());
		manipulatorStick.xButton.whenPressed(new StopTheFuels());
		manipulatorStick.yButton.whenPressed(new ShiftClimber(true));
		manipulatorStick.bButton.whenPressed(new ShiftClimber(false));

	}
}
