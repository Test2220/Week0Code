package org.usfirst.frc.team2220.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.*;

/**
 *
 */
public class TankDrive extends Subsystem
{

	// public static double scale = 1;
	public static double rDriveMotorSetpoint = 0;
	public static double lDriveMotorSetpoint = 0;

	public void resetEncoderPos()
	{
		rDriveMotorSetpoint = RobotMap.rDriveMaster.getPosition();
		lDriveMotorSetpoint = RobotMap.lDriveMaster.getPosition();
	//	RobotMap.rDriveMaster.setEncPosition(0);
	//	RobotMap.lDriveMaster.setEncPosition(0);
	}
	
	public void setRPosition(double val)
	{
		RobotMap.rDriveMaster.set(val);
	}
	
	public void setLPosition(double val)
	{
		RobotMap.lDriveMaster.set(val);
	}
	
	/**
	 * motion profiling setters
	 * @param x
	 */

	public void setCruiseVel(double x)
	{
		RobotMap.rDriveMaster.setMotionMagicCruiseVelocity(x);
		RobotMap.lDriveMaster.setMotionMagicCruiseVelocity(x);
	}
	
	public void setAccel(double x)
	{
		RobotMap.rDriveMaster.setMotionMagicAcceleration(x);
		RobotMap.lDriveMaster.setMotionMagicAcceleration(x);
	}
	// NEGATIVE FOR BACKWARDS
	public void incrementRPosition(double x)
	{
		rDriveMotorSetpoint += x;
		RobotMap.rDriveMaster.set(rDriveMotorSetpoint);
	}

	public void incrementLPosition(double x)
	{
		lDriveMotorSetpoint -= x;
		RobotMap.lDriveMaster.set(lDriveMotorSetpoint);
	}

	public void incrementAllPos(double x)
	{
		incrementRPosition(x);
		// incrementLPosition(x);
	}

	public boolean hasHitRSetpoint()
	{
		return Math.abs(RobotMap.rDriveMaster.getPosition() - rDriveMotorSetpoint) < 15;
	}

	public boolean hasHitLSetpoint()
	{
		return Math.abs(RobotMap.lDriveMaster.getPosition() - lDriveMotorSetpoint) < 15;
	}

	public void controllerTank(double rVal, double lVal)
	{
		RobotMap.rDriveMaster.set(rVal);
		RobotMap.lDriveMaster.set(lVal);
		shift(RobotMap.isInHighGear);
	}

	public void shift(boolean highGear)
	{
		if (highGear)
		{
			RobotMap.driveShifter.set(Value.kForward);
		}
		else
		{
			RobotMap.driveShifter.set(Value.kReverse);
		}
	}

	public void stopMotors()
	{
		RobotMap.rDriveMaster.set(0);
		RobotMap.lDriveMaster.set(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	@Override
	protected void initDefaultCommand()
	{
		// setDefaultCommand(new HoldMotionMode());
		//setDefaultCommand(new DriveOff());
	}
}
