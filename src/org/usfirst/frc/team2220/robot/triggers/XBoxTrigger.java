package org.usfirst.frc.team2220.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class XBoxTrigger extends Trigger
{
	GenericHID stick;
	int axis;
	double TRIGGERdVAL = 0.6;

	public XBoxTrigger(GenericHID stick, int x)
	{
		this.stick = stick;
		axis = x;
	}
	
	public XBoxTrigger(GenericHID stick, int axis, double driveDeadzone)
	{
		this.stick = stick;
		this.axis = axis;
		TRIGGERdVAL = driveDeadzone;
	}
	
	public void setDeadzone(double newDeadzone)
	{
		TRIGGERdVAL = newDeadzone;
	}

	public boolean get()
	{
		return getVal() < -TRIGGERdVAL || getVal() > TRIGGERdVAL;
	}
	
	public double getVal()
	{
		return stick.getRawAxis(axis);
	}
}
