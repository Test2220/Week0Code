package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.RunGearIntake;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem
{
	public static GearIntake instance_ = new GearIntake();

	public static GearIntake getInstance()
	{
		return instance_;
	}

	public void runJoysticks()
	{
		RobotMap.gearIntakeR.set(RobotMap.deadzone(OI.manipulatorStick.getRawAxis(1), 0.15) * -1.0);
		RobotMap.gearIntakeL.set(RobotMap.deadzone(OI.manipulatorStick.getRawAxis(1), 0.15));
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new RunGearIntake());
	}
}
