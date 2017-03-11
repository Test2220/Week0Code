package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CurveMotion extends Command {

	public static final double WHEEL_SPACING = 2.25; //feet
	double rDist, lDist;
	
	//theta in degrees, radii in feet, time in seconds
    public CurveMotion(double theta, double xRadius, double yRadius, boolean turnRight, double accelTime, double totalTime) 
    {
        requires(RobotMap.drive);
        
        if(turnRight) //left val includes robot wheel distance
        {
        	rDist = (theta / 360) * 2 * Math.PI * xRadius;
        	lDist = (theta / 360) * 2 * Math.PI * (xRadius + WHEEL_SPACING);
        }
        else 
        {
        	rDist = (theta / 360) * 2 * Math.PI * (xRadius + WHEEL_SPACING);
        	lDist = (theta / 360) * 2 * Math.PI * xRadius;
        }
        
        //the /2*60 scales from Feet/Second to Rotations/Minute
        //wheels go approx. 2 Feet/Rotation
        double rCruiseVel = ( (rDist / (totalTime - accelTime)) / 2 ) * 60;
        double lCruiseVel = ( (rDist / (totalTime - accelTime)) / 2 ) * 60;
        double rAccel = rCruiseVel / accelTime;
        double lAccel = lCruiseVel / accelTime;
        
        RobotMap.drive.setRCruiseVel(rCruiseVel);
        RobotMap.drive.setLCruiseVel(lCruiseVel);
        RobotMap.drive.setRAccel(rAccel);
        RobotMap.drive.setLAccel(lAccel);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	RobotMap.drive.resetEncoderPos();
    	
    	RobotMap.drive.incrementRPosition(rDist);
    	RobotMap.drive.incrementLPosition(lDist);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    }

    protected boolean isFinished() 
    {
    	boolean isRDriveFinished = ( Math.abs(RobotMap.rDriveMaster.getClosedLoopError()) < RobotMap.CLOSEDLOOPERROR );
    	boolean isLDriveFinished = ( Math.abs(RobotMap.rDriveMaster.getClosedLoopError()) < RobotMap.CLOSEDLOOPERROR );
        return isRDriveFinished && isLDriveFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	new DriveOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
