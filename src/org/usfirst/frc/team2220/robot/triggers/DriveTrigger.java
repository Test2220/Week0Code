package org.usfirst.frc.team2220.robot.triggers;

import org.usfirst.frc.team2220.robot.OI;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DriveTrigger extends Trigger
{
	public boolean get()
	{
		if(OI.driverStick.rightYAxis.get() || OI.driverStick.leftYAxis.get())
			return true;
		return false;
	}
}
