package org.usfirst.frc.team2220.robot.triggers;

import org.usfirst.frc.team2220.robot.OI;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DriveTrigger extends Trigger
{

	//there is some weird behavior when using one stick versus the other and only requiring one to pass the deadzone
	//it's not really worth time to solve it, but making a general trigger class could be involved?
	//talk to Tim TODO

	public boolean get()
	{
		double driveDZ = 0.15;
		double lVal = OI.driverStick.getRawAxis(OI.LEFT_Y_AXIS);
		double rVal = OI.driverStick.getRawAxis(OI.RIGHT_Y_AXIS) * -1;
		if (Math.abs(lVal) > driveDZ || Math.abs(rVal) > driveDZ)
			return true;
		return false;
	}
}
