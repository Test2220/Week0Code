package org.usfirst.frc.team2220.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.DriveWithControllers;;

/**
 *
 */
public class TankDrive extends Subsystem {

	public static double scale = 1;
	public static double rDriveMotorSetpoint = 0;
	public static double lDriveMotorSetpoint = 0;
	//NEGATIVE FOR BACKWARDS
	public void incrementRPosition(double x)
	{
		rDriveMotorSetpoint += x;
		RobotMap.rDriveMaster.set(rDriveMotorSetpoint);
	}
	
	public void incrementLPosition(double x)
	{
		lDriveMotorSetpoint += x;
		RobotMap.lDriveMaster.set(lDriveMotorSetpoint);
	}
	
	public void incrementAllPos(double x)
	{
		incrementRPosition(x);
		incrementLPosition(x);
	}
	
	public boolean hasHitRSetpoint()
	{
		return Math.abs(RobotMap.rDriveMaster.getPosition() - rDriveMotorSetpoint) < 15;
	}
	public boolean hasHitLSetpoint()
	{
		return Math.abs(RobotMap.lDriveMaster.getPosition() - lDriveMotorSetpoint) < 15;
	}
	
	
	public void controllerTank()
	{
		double driveDZ = 0.15;
		double lVal = RobotMap.deadzone(OI.driverStick.getRawAxis(OI.lAxis) * scale, driveDZ);
		double rVal = RobotMap.deadzone(OI.driverStick.getRawAxis(OI.rAxis) * scale * -1, driveDZ);
		RobotMap.rDriveMaster.set(rVal);
		RobotMap.lDriveMaster.set(lVal);
		shift(RobotMap.isInHighGear);
	}
	
	public void shift(boolean highGear)
	{
		if(highGear)
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

    public double getScale()
    {
    	return TankDrive.scale;
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	@Override
    protected void initDefaultCommand()
    {
    	setDefaultCommand(new DriveWithControllers());
    }
}

