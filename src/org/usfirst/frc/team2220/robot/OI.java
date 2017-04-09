package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.*;
import org.usfirst.frc.team2220.robot.triggers.DriveTrigger;
import org.usfirst.frc.team2220.robot.triggers.XboxTrigger;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public static XboxController driverStick = new XboxController(0);
	public static XboxController manipulatorStick = new XboxController(1);
	public static int motorValueOff = 0;

	//if i have these constants should i rethink naming the actual buttons and triggers?
	//maybe make my own class that pre-declares all of these per xbox controller, but still allows binding to buttons?
	//talk to Tim TODO

	//Axis constants
	public static final int LEFT_Y_AXIS = 1;
	public static final int RIGHT_Y_AXIS = 5;

	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;

	//Button Constants
	public static final int A_BUTTON = 1;
	public static final int B_BUTTON = 2;
	public static final int X_BUTTON = 3;
	public static final int Y_BUTTON = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;

	/**
	 * DRIVER TRIGGERS
	 */

	private static Button aButtonD = new JoystickButton(driverStick, A_BUTTON);
	private static Button bButtonD = new JoystickButton(driverStick, B_BUTTON);
	private static Button xButtonD = new JoystickButton(driverStick, X_BUTTON);
	private static Button yButtonD = new JoystickButton(driverStick, Y_BUTTON);

	private static Button lBumperD = new JoystickButton(manipulatorStick, LEFT_BUMPER);
	private static Button rBumperD = new JoystickButton(manipulatorStick, RIGHT_BUMPER);

	private static DriveTrigger isDriving = new DriveTrigger();

	/**
	 * MANIPULATOR TRIGGERS
	 */
	private static Button aButtonM = new JoystickButton(manipulatorStick, A_BUTTON);
	private static Button bButtonM = new JoystickButton(manipulatorStick, B_BUTTON);
	private static Button xButtonM = new JoystickButton(manipulatorStick, X_BUTTON);
	private static Button yButtonM = new JoystickButton(manipulatorStick, Y_BUTTON);
	private static Button lBumperM = new JoystickButton(manipulatorStick, LEFT_BUMPER);
	private static Button rBumperM = new JoystickButton(manipulatorStick, RIGHT_BUMPER);

	private static XboxTrigger lTriggerM = new XboxTrigger(manipulatorStick, LEFT_TRIGGER);

	private static XboxTrigger rTriggerM = new XboxTrigger(manipulatorStick, RIGHT_TRIGGER);

	public static void init()
	{
		//////////
		// Driver//
		//////////

		yButtonD.whenPressed(new TeleMotion(2.5, 2.5, 500, 1000));
		/* yButtonD.whenPressed(new TeleMotion(56, 56, 500, 1000)); //go forward
		 * xButtonD.whenPressed(new TeleMotion(7.25, -7.25, 100, 500)); //turn */

		aButtonD.whenPressed(new ShiftDrivetrain(true));
		xButtonD.whenPressed(new ShiftDrivetrain(false));

		isDriving.whileActive(new DriveWithControllers());
		isDriving.whenInactive(new DriveOff());

		///////////////
		//Manipulator//
		///////////////

		rTriggerM.whileActive(new AccelerateClimber(1.0)); //this is positive, flips in command
		lTriggerM.whileActive(new RunClimber(-1.0)); //this is negative, doesn't flip in command

		lBumperM.whileHeld(new RunWasher(1.0));
		rBumperM.whileHeld(new RunWasher(-1.0));
		aButtonM.whenPressed(new RootyTootyPointAndShooty());
		xButtonM.whenPressed(new StopTheFuels());
		yButtonM.whenPressed(new ShiftClimber(true));
		bButtonM.whenPressed(new ShiftClimber(false));

	}
}
