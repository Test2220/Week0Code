package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class FBGyroMotion extends Command
{

	double motorSetpointR = 0, motorSetpointL = 0; //initialVal
	double prevMotorR = 0, prevMotorL = 0;
	double gyroAllowableError = 0.5;
	double prevGyro = 0, gyroSetpoint;
	Gyro gyro;
	
	public FBGyroMotion(double gsp, Gyro g) {
        requires(RobotMap.drive);
        gyro = g;
        gyroSetpoint = gsp;
    }

	// Called just before this Command runs the first time
	protected void initialize()
	{
		RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	RobotMap.drive.resetEncoderPos();
    	gyro.reset();
    	/*
    	if(gyroSetpoint > 0) //CW
    	{
    		motorSetpointR = 0.1;
    		motorSetpointL = -0.1;
    	}
    	else //CCW
    	{
    		motorSetpointR = -0.1;
    		motorSetpointL = 0.1;
    	}
    	*/
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double currentGyro = gyro.getAngle();
		double diffGyro = currentGyro - prevGyro;
		

		double currentMotorR = RobotMap.rDriveMaster.getPosition();
		double currentMotorL = RobotMap.lDriveMaster.getPosition();
		double diffR = currentMotorR - prevMotorR;
		double diffL = currentMotorL - prevMotorL;
		
		if(diffR == 0 || diffGyro == 0 || diffL == 0) //initially, stuck, hit setpoint
		{
			if(gyroSetpoint > 0) //CW
	    	{
				motorSetpointR += 0.1;
				motorSetpointL -= 0.1;
	    	}
	    	else //CCW
	    	{
	    		motorSetpointR -= 0.1;
				motorSetpointL += 0.1;
	    	}
		}
		else
		{
			motorSetpointR = (diffR / diffGyro) * (gyroSetpoint - currentGyro);
			motorSetpointL = (diffL / diffGyro) * (gyroSetpoint - currentGyro);
		}
		
		RobotMap.drive.setRPosition(motorSetpointR);
		RobotMap.drive.setLPosition(motorSetpointL);
		
		prevGyro = currentGyro;
		prevMotorR = currentMotorR;
		prevMotorL = currentMotorL;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Math.abs(gyro.getAngle() - gyroSetpoint) < gyroAllowableError;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		new DriveOff();
		RobotMap.drive.resetEncoderPos();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
