package org.usfirst.frc.team2220.robot.triggers;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class XboxTrigger extends Trigger
{
	GenericHID stick;
	int axis;
	final double TRIGGERdVAL = 0.6;
	
	public XboxTrigger(GenericHID stick, int x)
	{
		this.stick = stick;
		axis = x;
	}
	
    public boolean get() 
    {
    	return stick.getRawAxis(axis) > TRIGGERdVAL;
    }
}
