package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FBCameraMotion extends Command
{

	//double motorSetpointR = 0, motorSetpointL = 0; //initialVal
	double prevMotorR = 0, prevMotorL = 0;
	double allowableError = 10;
	double prevCamVal = 0, camSetpoint = 60;//160
	double midpoint = 100000;
	NetworkTable contourTable;
	Timer t;
	double endVal;
	
	public FBCameraMotion(double timeVal) 
	{
		endVal = timeVal;
        requires(RobotMap.drive);
    }

	// Called just before this Command runs the first time
	protected void initialize()
	{
		t = new Timer();
    	t.reset();
    	t.start();
		RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	RobotMap.drive.resetEncoderPos();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		contourTable = NetworkTable.getTable("GRIP/contoursReport");
		double[] centerX = contourTable.getNumberArray("centerX", new double[]{});
		double[] area    = contourTable.getNumberArray("area", new double[]{});
		System.out.println("Contour 1");
		if(centerX.length >= 1 && area.length >= 1)
		{
			midpoint = centerX[0];
				
			System.out.println("midpoint = " + midpoint);
		
			double setpointChangeVal = 0.04;
			if(midpoint > camSetpoint)
			{
				RobotMap.drive.incrementAllPos(-setpointChangeVal);
			}
			else if(midpoint < camSetpoint)
			{
				RobotMap.drive.incrementAllPos(setpointChangeVal);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if(t.get() > endVal)
			return true;
		return Math.abs(camSetpoint - midpoint) < allowableError;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		new DriveOff();
	//	RobotMap.drive.resetEncoderPos();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}