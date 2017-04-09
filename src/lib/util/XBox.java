package lib.util;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.triggers.XBoxTrigger;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XBox extends XboxController
{

	public Button aButton, bButton, xButton, yButton, leftBumper, rightBumper;
	public XBoxTrigger leftYAxis, rightYAxis, leftTrigger, rightTrigger;

	public XBox(int port)
	{
		super(port);
		aButton = new JoystickButton(this, 1);
		bButton = new JoystickButton(this, 2);
		xButton = new JoystickButton(this, 3);
		yButton = new JoystickButton(this, 4);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);

		//@formatter:off -> nice indentation makes ez to read
		leftYAxis    = new XBoxTrigger(this, 1, RobotMap.DRIVE_DEADZONE);
		rightYAxis   = new XBoxTrigger(this, 5, RobotMap.DRIVE_DEADZONE);
		
		leftTrigger  = new XBoxTrigger(this, 2, 0.6);
		rightTrigger = new XBoxTrigger(this, 3, 0.6);
		//@formatter:on
	}

}
