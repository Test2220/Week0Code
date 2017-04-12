package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.subsystems.TankDrive;
import org.usfirst.frc.team2220.robot.triggers.XBoxTrigger;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithControllers extends Command
{
	private XBoxTrigger rightTrigger, leftTrigger;

	public DriveWithControllers(XBoxTrigger rightTrigger, XBoxTrigger leftTrigger)
	{
		this.rightTrigger = rightTrigger;
		this.leftTrigger = leftTrigger;
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
		double rVal = rightTrigger.getVal() * scale * -1.0;
		double lVal = leftTrigger.getVal()  * scale;
		
		rVal = RobotMap.deadzone(rVal, RobotMap.DRIVE_DEADZONE);
		lVal = RobotMap.deadzone(lVal, RobotMap.DRIVE_DEADZONE);
		TankDrive.getInstance().controllerTank(rVal, lVal); 
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
