package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FBCameraMotion extends Command
{

	double motorSetpointR = 0, motorSetpointL = 0; //initialVal
	double prevMotorR = 0, prevMotorL = 0;
	double allowableError = 10;
	double prevCamVal = 0, camSetpoint = 60;//160
	//double RTLVal = 100000;
	double midpoint = 100000;
	NetworkTable contourTable;
	
	public FBCameraMotion() 
	{
        requires(RobotMap.drive);
    }

	// Called just before this Command runs the first time
	protected void initialize()
	{
		RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	motorSetpointR = RobotMap.rDriveMaster.getPosition();
    	motorSetpointL = RobotMap.lDriveMaster.getPosition();
    	//RobotMap.drive.resetEncoderPos();
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
		contourTable = NetworkTable.getTable("GRIP/contoursReport");
		double[] centerX = contourTable.getNumberArray("centerX", new double[]{});
		double[] area    = contourTable.getNumberArray("area", new double[]{});
		//double minX = 320, maxX = 0; //lol
		System.out.println("Contour 1");
		if(centerX.length >= 1 && area.length >= 1)
		{
				//double desiredMiddle = 100;//160;
			midpoint = centerX[0];
				
			System.out.println("midpoint = " + midpoint);
		//	RTLVal = midPoint - camSetpoint;
				//6400 +- 300
			//System.out.println(RTLVal);
			//double range = 30;
			//double kP = -0.1;
			//double motorVal = kP * RTLVal;//0.2;
			double diffCam = midpoint - prevCamVal;
			
	
			double currentMotorR = RobotMap.rDriveMaster.getPosition();
			//double currentMotorL = RobotMap.lDriveMaster.getPosition();
		//	double diffR = currentMotorR - prevMotorR;
		//	double diffL = currentMotorL - prevMotorL;
			double setpointChangeVal = 0.04;
			if(midpoint > camSetpoint)
			{
				motorSetpointR -= setpointChangeVal;
				motorSetpointL -= setpointChangeVal;
			}
			else if(midpoint < camSetpoint)
			{
				motorSetpointR += setpointChangeVal;
				motorSetpointL += setpointChangeVal;
			}
			/*
			if(diffR == 0 || diffCam == 0/* || diffL == 0*) //initially, stuck, hit setpoint
			{
				
				SmartDashboard.putBoolean("nudge", true);
				if(camSetpoint > 0) //CW
		    	{
					motorSetpointR += 0.1;
					motorSetpointL -= 0.1;//-= 0.1;
		    	}
		    	else //CCW
		    	{
		    		motorSetpointR -= 0.1;
					motorSetpointL += 0.1;//+= 0.1;
		    	}
		    	
			}
			else //normal calc
			{
				
				SmartDashboard.putBoolean("nudge", false);
			//	motorSetpointR = (diffR / diffCam) * (camSetpoint - midpoint);
			///	motor
			//	motorSetpointL = (diffL / diffCam) * (camSetpoint - midpoint);
				
			}
			*/
			
			RobotMap.drive.setRPosition(motorSetpointR);
			RobotMap.drive.setLPosition(motorSetpointL);
			
			//prevCamVal = midpoint;
			//prevMotorR = currentMotorR;
			//prevMotorL = currentMotorL;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Math.abs(camSetpoint - midpoint) < allowableError;
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
