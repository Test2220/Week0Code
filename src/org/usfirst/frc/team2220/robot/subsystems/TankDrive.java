package org.usfirst.frc.team2220.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class TankDrive extends Subsystem
{
	
	
	private static TankDrive instance_ = new TankDrive();
	private boolean shiftState;
	
	private static CANTalon rDriveMaster, rDriveSlave;
	private static CANTalon lDriveMaster, lDriveSlave;
	
	public static double rDriveMotorSetpoint = 0;
	public static double lDriveMotorSetpoint = 0;
	
	public final static int CLOSEDLOOPERROR = 30; //this is in native units so inchesToEncRev doesn't apply? TODO
	public final static double DRIVE_DEADZONE = 0.15;
	
	public static TankDrive getInstance()
	{
		return instance_;
	}
	
	public TankDrive()
	{
		rDriveMaster = new CANTalon(RobotMap.RIGHT_DRIVE_MASTER);
		rDriveSlave  = new CANTalon(RobotMap.RIGHT_DRIVE_SLAVE);
		lDriveMaster = new CANTalon(RobotMap.LEFT_DRIVE_MASTER);
		lDriveSlave  = new CANTalon(RobotMap.LEFT_DRIVE_SLAVE);
		
		rDriveSlave.changeControlMode(TalonControlMode.Follower);
		rDriveSlave.set(rDriveMaster.getDeviceID());
		
		lDriveSlave.changeControlMode(TalonControlMode.Follower);
		lDriveSlave.set(lDriveMaster.getDeviceID());
		
		// TODO remove
		int accel = 400, cruiseVel = 400;

		rDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rDriveMaster.reverseSensor(true);
		rDriveMaster.configEncoderCodesPerRev(256); // 4 x 256 = 1024
		
		rDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

		rDriveMaster.setEncPosition(0);

		double kP = 2.0, kI = 0.0015, kD = 0.0;
		rDriveMaster.setF(240);  //encoder ticks per 100ms -> 9.34 RPS
		rDriveMaster.setPID(kP, kI, kD);
		rDriveMaster.setMotionMagicAcceleration(accel); // RPM/S
		rDriveMaster.setMotionMagicCruiseVelocity(cruiseVel); // RPM

		lDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		lDriveMaster.reverseSensor(false);
		lDriveMaster.configEncoderCodesPerRev(256);
		lDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

		lDriveMaster.setEncPosition(0);

		lDriveMaster.setF(240); //encoder ticks per 100ms -> 9.34 RPS
		lDriveMaster.setPID(kP, kI, kD); 
		lDriveMaster.setMotionMagicAcceleration(accel); // RPM/S
		lDriveMaster.setMotionMagicCruiseVelocity(cruiseVel); // RPM
	}
	
	public boolean getShiftState()
	{
		return shiftState;
	}

	public void resetEncoderPos()
	{
		rDriveMotorSetpoint = rDriveMaster.getPosition();
		lDriveMotorSetpoint = lDriveMaster.getPosition();
	}
	
	public double getRPosition()
	{
		return rDriveMaster.getPosition();
	}
	
	public double getLPosition()
	{
		return lDriveMaster.getPosition();
	}
	
	public void setRPosition(double position)
	{
		rDriveMotorSetpoint = position;
		rDriveMaster.set(rDriveMotorSetpoint);
	}
	
	public void setLPosition(double position)
	{
		lDriveMotorSetpoint = position;
		lDriveMaster.set(lDriveMotorSetpoint);
	}
	
	public void changeToMotionMagic()
	{
		rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
		lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
	}
	
	public void changeToPercentVBus()
	{
		rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
	}

	/**
	 * motion profiling setters
	 * 
	 * @param x
	 */

	public void setBothCruiseVel(double x)
	{
		setRCruiseVel(x);
		setLCruiseVel(x);
	}

	public void setRCruiseVel(double x)
	{
		rDriveMaster.setMotionMagicCruiseVelocity(x);
	}

	public void setLCruiseVel(double x)
	{
		lDriveMaster.setMotionMagicCruiseVelocity(x);
	}

	public void setBothAccel(double x)
	{
		setRAccel(x);
		setLAccel(x);
	}

	public void setRAccel(double x)
	{
		rDriveMaster.setMotionMagicAcceleration(x);
	}

	public void setLAccel(double x)
	{
		lDriveMaster.setMotionMagicAcceleration(x);
	}

	// NEGATIVE FOR BACKWARDS
	public void incrementRPosition(double x)
	{
		rDriveMotorSetpoint += x;
		rDriveMaster.set(rDriveMotorSetpoint);
	}

	public void incrementLPosition(double x)
	{
		lDriveMotorSetpoint -= x;
		lDriveMaster.set(lDriveMotorSetpoint);
	}

	public void incrementAllPos(double x)
	{
		incrementRPosition(x);
		incrementLPosition(x);
	}

	public boolean hasHitRSetpoint()
	{
		return Math.abs(rDriveMaster.getClosedLoopError()) < CLOSEDLOOPERROR;
	}

	public boolean hasHitLSetpoint()
	{
		return Math.abs(lDriveMaster.getClosedLoopError()) < CLOSEDLOOPERROR;
	}

	public void controllerTank(double rVal, double lVal)
	{
		rDriveMaster.set(rVal);
		lDriveMaster.set(lVal);
	}

	public void shift(boolean highGear)
	{
		shiftState = highGear;
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
		rDriveMaster.set(0);
		lDriveMaster.set(0);
	}

	@Override
	protected void initDefaultCommand()
	{
		// no default command
	}
}
