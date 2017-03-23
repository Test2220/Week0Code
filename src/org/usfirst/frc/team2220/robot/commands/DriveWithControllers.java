package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithControllers extends Command
{

	public DriveWithControllers()
	{
		requires(RobotMap.drive);
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
		double rVal = OI.driverStick.getRawAxis(OI.rAxis) * scale * -1;
		double lVal = OI.driverStick.getRawAxis(OI.lAxis) * scale;
		RobotMap.drive.controllerTank(rVal, lVal); // method on instantiation of
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
