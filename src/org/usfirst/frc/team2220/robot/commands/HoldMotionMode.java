package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldMotionMode extends Command {

    public HoldMotionMode() {
    	requires(RobotMap.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.rDriveMaster.set(TankDrive.rDriveMotorSetpoint);
    	RobotMap.lDriveMaster.set(TankDrive.lDriveMotorSetpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return RobotMap.drive.hasHitLSetpoint() && RobotMap.drive.hasHitRSetpoint();
        // return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
