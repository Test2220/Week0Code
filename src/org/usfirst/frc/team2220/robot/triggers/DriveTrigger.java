package org.usfirst.frc.team2220.robot.triggers;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DriveTrigger extends Trigger
{

	double scale = 1.0;
	public boolean get()
	{
		double rVal = OI.driverStick.rightYAxis.getVal() * scale * -1.0;
		double lVal = OI.driverStick.leftYAxis.getVal()  * scale;
		/*
		double lVal = OI.driverStick.getRawAxis(OI.LEFT_Y_AXIS);
		double rVal = OI.driverStick.getRawAxis(OI.RIGHT_Y_AXIS) * -1;
		*/
		if (Math.abs(lVal) > RobotMap.DRIVE_DEADZONE || Math.abs(rVal) > RobotMap.DRIVE_DEADZONE)
			return true;
		return false;
	}
}
