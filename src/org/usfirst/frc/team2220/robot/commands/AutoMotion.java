package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoMotion extends Command {

	double rVal, lVal;
	double a, v;
	int doneCounter = 0;
	//input is encoder revolutions, along with velocity in RPM and accel in RPM/s
	//8enc:1wheel rotation
	Timer t;
	double endVal;
    public AutoMotion(double r, double l, double accel, double cVel, double timeEnd) {
        requires(RobotMap.drive);
        rVal = r;
        lVal = l;
        
        a = accel;
        v = cVel;
        endVal = timeEnd;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	t = new Timer();
    	t.reset();
    	t.start();
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
    	if(t.get() == 0)
    		t.start();
    	SmartDashboard.putBoolean("isRunningTeleMotion", true);
    	SmartDashboard.putNumber("doneCounter", doneCounter);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(t.get() > endVal)
			return true;
        if (RobotMap.drive.hasHitRSetpoint() && RobotMap.drive.hasHitLSetpoint())
        	doneCounter++;
        else doneCounter = 0;
        return doneCounter > 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    	t.reset();
    	new DriveOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	t.reset();
    	System.out.println("interupted");
    }
}
