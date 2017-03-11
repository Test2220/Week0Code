package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ApproachGear extends Command {

	NetworkTable contourTable;
//	TeleMotion motionCommand;
	boolean motionStarted = false;
	boolean isDone = false;
	
    public ApproachGear() {
    	requires(RobotMap.drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }
    
    public void startTeleMotion(double rVal, double lVal)
    {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	RobotMap.drive.resetEncoderPos();
    	
    	RobotMap.drive.incrementRPosition(rVal);
    	RobotMap.drive.incrementLPosition(lVal);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(!motionStarted)
    	{
			contourTable = NetworkTable.getTable("GRIP/contoursReport");
			double[] centerX = contourTable.getNumberArray("centerX", new double[]{});
			double[] area    = contourTable.getNumberArray("area", new double[]{});
			//double minX = 320, maxX = 0; //lol
			System.out.println("Contour 1");
			if(centerX.length >= 1 && area.length >= 1)
			{
				double desiredMiddle = 100;//160;
				double midPoint = centerX[0];
				//System.out.println("Image middle = 160");
				//System.out.println("Point middle = " + midPoint);
				
				double RTLVal = midPoint - desiredMiddle;
				//6400 +- 300
				System.out.println(RTLVal);
				double range = 30;
				double kP = -0.1;
				double motorVal = kP * RTLVal;//0.2;
				
				double minMotorVal = 0;
				//System.out.println(motorVal);
				if(motorVal > 0 && motorVal < minMotorVal)
				{
					motorVal = minMotorVal;
					System.out.println("LEFT");
				}
				if(motorVal < 0 && motorVal > -minMotorVal)
				{
					motorVal = -minMotorVal;
					System.out.println("RIGHT");
				}
				if(RTLVal > range || RTLVal < -range)
				{
					motionStarted = true;
					SmartDashboard.putNumber("motionVal", motorVal);
					startTeleMotion(motorVal, -motorVal);
					/*
					if(motionCommand == null)
					{
						SmartDashboard.putNumber("motionVal", motorVal);
						motionCommand = new TeleMotion(motorVal, -motorVal);
						motionCommand.start();
					}
					else if(motionCommand.isFinished())
					{
						motionCommand = null;
						isDone = true;
					}
					*/
				}
				else //lined up
				{
					isDone = true;
					/*
					if(motionCommand != null)
					{
						motionCommand.end();
						motionCommand = null;
					}
					*/
					/*
					double currentRange = area[0];
					double FTBRange = 300;
					double FTBTarget = 6400;
					double FTBVal = FTBTarget - currentRange;
					if(FTBVal > FTBRange || FTBVal < - FTBRange)
					{
						//today, feb13th at 20:34, calvin swanson made us sad
						//pretty much a continual thing
					}
					else
					{
						RobotMap.rightMaster.set(0);
						RobotMap.leftMaster.set(0);
					}
					*/
				}
				
			}
    	}
		//robotUsingContourList = false;
	}
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(motionStarted)
    	{
	    	boolean isRDriveFinished = ( Math.abs(RobotMap.rDriveMaster.getClosedLoopError()) < RobotMap.CLOSEDLOOPERROR );
	    	boolean isLDriveFinished = ( Math.abs(RobotMap.rDriveMaster.getClosedLoopError()) < RobotMap.CLOSEDLOOPERROR );
	        return isRDriveFinished && isLDriveFinished;
    	}
    	return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
