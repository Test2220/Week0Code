package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveOff extends InstantCommand {

	public DriveOff() {
        super();
        requires(RobotMap.drive);
    }

    // Called once when the command executes
    protected void initialize() 
    {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.drive.stopMotors();
    	SmartDashboard.putBoolean("isRunningTeleMotion", false);
    }

}
