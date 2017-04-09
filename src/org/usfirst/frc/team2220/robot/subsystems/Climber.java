package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.ClimberTrigger;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem
{
	public static Climber instance_ = new Climber();
	public boolean shiftState;

	public static Climber getInstance()
	{
		return instance_;
	}
	public void climbWithTrigger()
	{
		run(RobotMap.deadzone( OI.manipulatorStick.getRawAxis(3), 0.1 ) * 1.0);
	}

	public void run(double val)
	{
		RobotMap.climberMaster.set(val);
	}

	public void shift(boolean highGear)
	{
		shiftState = highGear;
		if (highGear)
		{
			RobotMap.collectorShifter.set(Value.kForward);
		}
		else
		{
			RobotMap.collectorShifter.set(Value.kReverse);
		}
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new ClimberTrigger());
	}
}
