package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithControllers extends Command
{

	public DriveWithControllers()
	{
		requires(TankDrive.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		RobotMap.rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double scale = 1.0;
		double rVal = OI.driverStick.rightYAxis.getVal() * scale * -1.0;
		double lVal = OI.driverStick.leftYAxis.getVal()  * scale;
		
		/*
		double rVal = OI.driverStick.getRawAxis(OI.RIGHT_Y_AXIS) * scale * -1;
		double lVal = OI.driverStick.getRawAxis(OI.LEFT_Y_AXIS) * scale;
		*/
		rVal = RobotMap.deadzone(rVal, RobotMap.DRIVE_DEADZONE);
		lVal = RobotMap.deadzone(lVal, RobotMap.DRIVE_DEADZONE);
		TankDrive.getInstance().controllerTank(rVal, lVal); // method on instantiation of
		// TankDrive subclass of
		// subsystem

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		new DriveOff();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		new DriveOff();
	}
}
