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

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void runJoysticks()
	{
		RobotMap.intakeMotor.set(RobotMap.deadzone(OI.manipulatorStick.getRawAxis(1), 0.15) * -1);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new RunGearIntake());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
