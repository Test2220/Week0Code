package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem
{
	private static GearIntake instance_ = new GearIntake();
	private static CANTalon intakeR, intakeL;
	
	public static GearIntake getInstance()
	{
		return instance_;
	}
	
	public GearIntake()
	{
		intakeR = new CANTalon(RobotMap.RIGHT_GEAR_INTAKE);
		intakeL = new CANTalon(RobotMap.LEFT_GEAR_INTAKE);
	}

	public void setMotors(double motorVal)
	{
		intakeR.set(motorVal * -1.0);
		intakeL.set(motorVal);
	}

	protected void initDefaultCommand() 
	{
	}

}
