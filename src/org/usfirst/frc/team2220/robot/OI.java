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
public class OI {
	public static XboxController driverStick = new XboxController(0);
	public static XboxController manipulatorStick = new XboxController(1);
	public static int lAxis = 1;
	public static int rAxis = 5;
	public static int motorValueOff = 0;
	
	
	/**
	 * DRIVER TRIGGERS
	 */
	
	private static Button aButtonD = new JoystickButton(driverStick, 1);
	private static Button bButtonD = new JoystickButton(driverStick, 2);
	private static Button xButtonD = new JoystickButton(driverStick, 3);
	private static Button yButtonD = new JoystickButton(driverStick, 4);
	
	private static Button lBumperD = new JoystickButton(manipulatorStick, 5);
	private static Button rBumperD = new JoystickButton(manipulatorStick, 6);
	
	private static DriveTrigger isDriving = new DriveTrigger();
	
	
	/**
	 * MANIPULATOR TRIGGERS
	 */
	private static Button aButtonM = new JoystickButton(manipulatorStick, 1);
	private static Button bButtonM = new JoystickButton(manipulatorStick, 2);
	private static Button xButtonM = new JoystickButton(manipulatorStick, 3);
	private static Button yButtonM = new JoystickButton(manipulatorStick, 4);
	private static Button lBumperM = new JoystickButton(manipulatorStick, 5);
	private static Button rBumperM = new JoystickButton(manipulatorStick, 6);
	
	public static final int LTRIGGER = 2, RTRIGGER = 3;
	private static XboxTrigger lTriggerM = new XboxTrigger(manipulatorStick, 2);
	
	
	//private static XboxTrigger rTriggerM = new XboxTrigger(manipulatorStick, 3);

	public static void init()
	{
		//////////
		//Driver//
		//////////
		
		yButtonD.whenPressed(new TeleMotion(2.5, 2.5, 500, 1000));
		/*
		yButtonD.whenPressed(new TeleMotion(56, 56, 500, 1000)); //go forward
		xButtonD.whenPressed(new TeleMotion(7.25, -7.25, 100, 500)); //turn
		*/
		
		aButtonD.whenPressed(new ShiftDrivetrain(true));
		xButtonD.whenPressed(new ShiftDrivetrain(false));
		
		isDriving.whileActive(new DriveWithControllers());
		isDriving.whenInactive(new DriveOff());
		
		///////////////
		//Manipulator//
		///////////////
		
		lTriggerM.whileActive(new RunIntake(1.0));
		//	rTriggerM.whileActive(new RunIntake(-1.0)); //doing this could break ratchet
		lBumperM.whileHeld(new RunWasher(1.0));
		rBumperM.whileHeld(new RunWasher(-1.0));
		aButtonM.whenPressed(new RootyTootyPointAndShooty());
		xButtonM.whenPressed(new StopTheFuels());
		yButtonM.whenPressed(new ShiftCollector(true));
		bButtonM.whenPressed(new ShiftCollector(false));
		
	}
}
