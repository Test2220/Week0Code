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
	double rCruiseVel, lCruiseVel, rAccel, lAccel;
	
	//theta in degrees, radii in feet, time in seconds
    public CurveMotion(double theta, double radius, boolean turnRight, double accelTime, double totalTime) 
    {
        requires(RobotMap.drive);
        
        if(turnRight) //left val includes robot wheel distance
        {
        	rDist = (theta / 360) * 2 * Math.PI * radius;
        	lDist = (theta / 360) * 2 * Math.PI * (radius + WHEEL_SPACING);
        }
        else 
        {
        	rDist = (theta / 360) * 2 * Math.PI * (radius + WHEEL_SPACING);
        	lDist = (theta / 360) * 2 * Math.PI * radius;
        }
        
        //the /2*60 scales from Feet/Second to Rotations/Minute
        //wheels go approx. 2 Feet/Rotation
        rCruiseVel = ( (rDist / (totalTime - accelTime)) / 2 ) * 60;
        lCruiseVel = ( (lDist / (totalTime - accelTime)) / 2 ) * 60;
        rAccel = rCruiseVel / accelTime;
        lAccel = lCruiseVel / accelTime;
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    	
    	RobotMap.drive.setRCruiseVel(rCruiseVel);
        RobotMap.drive.setLCruiseVel(lCruiseVel);
        RobotMap.drive.setRAccel(rAccel);
        RobotMap.drive.setLAccel(lAccel);
    	
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
        return RobotMap.drive.hasHitRSetpoint() && RobotMap.drive.hasHitLSetpoint();
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
