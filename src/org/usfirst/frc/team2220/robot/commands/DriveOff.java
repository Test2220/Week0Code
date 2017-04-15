package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveOff extends InstantCommand
{

	public DriveOff()
	{
		super();
		requires(TankDrive.getInstance());
	}

	// Called once when the command executes
	protected void initialize()
	{
		TankDrive.getInstance().changeToPercentVBus();
		TankDrive.getInstance().stopMotors();
		SmartDashboard.putBoolean("isRunningTeleMotion", false);
	}

}
