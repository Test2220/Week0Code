package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem
{
	private static Climber instance_ = new Climber();
	private boolean shiftState;
	
	private static CANTalon climberMaster, climberSlave;
	
	
	public static Climber getInstance()
	{
		return instance_;
	}
	
	public Climber()
	{
		climberMaster = new CANTalon(RobotMap.CLIMBER_MASTER);
		climberSlave  = new CANTalon(RobotMap.CLIMBER_SLAVE);
		
		climberSlave.changeControlMode(TalonControlMode.Follower);
		climberSlave.set(climberMaster.getDeviceID());
	}
	
	public boolean getShiftState()
	{
		return shiftState;
	}
	
	public void run(double val)
	{
		climberMaster.set(val);
	}
	
	public double getRunVal()
	{
		return climberMaster.get();
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
		//setDefaultCommand(new DeccelerateClimber());
	}
}
