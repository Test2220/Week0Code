package org.usfirst.frc.team2220.robot.triggers;

import org.usfirst.frc.team2220.robot.OI;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DriveTrigger extends Trigger {

    public boolean get() {
    	double driveDZ = 0.15;
		double lVal = OI.driverStick.getRawAxis(OI.lAxis);
		double rVal = OI.driverStick.getRawAxis(OI.rAxis) * -1;
		if( Math.abs(lVal) > driveDZ || Math.abs(rVal) > driveDZ)
			return true;
        return false;
    }
}
