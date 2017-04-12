package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem
{
	private static GearIntake instance_ = new GearIntake();

	public static GearIntake getInstance()
	{
		return instance_;
	}

	public void setMotors(double motorVal)
	{
		RobotMap.gearIntakeR.set(motorVal * -1.0);
		RobotMap.gearIntakeL.set(motorVal);
	}

	protected void initDefaultCommand() 
	{
	}

}
