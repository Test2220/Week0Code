package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleMotion extends Command {

	double rVal, lVal;
	double a, v;
	//input is encoder revolutions, along with velocity in RPM and accel in RPM/s
	//8enc:1wheel rotation
    public TeleMotion(double r, double l, double accel, double cVel) {
        requires(RobotMap.drive);
        rVal = r;
        lVal = l;
        
        a = accel;
        v = cVel;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	RobotMap.rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
        RobotMap.lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
        RobotMap.drive.setBothAccel(a);
        RobotMap.drive.setBothCruiseVel(v);
        
    	RobotMap.drive.resetEncoderPos();
    	
    	RobotMap.drive.incrementRPosition(rVal);
    	RobotMap.drive.incrementLPosition(lVal);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	SmartDashboard.putBoolean("isRunningTeleMotion", true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return RobotMap.drive.hasHitRSetpoint() && RobotMap.drive.hasHitLSetpoint();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//new DriveOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}
