package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem
{
	private static Climber instance_ = new Climber();
	private boolean shiftState;

	public static Climber getInstance()
	{
		return instance_;
	}
	
	public boolean getShiftState()
	{
		return shiftState;
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
	}
}
