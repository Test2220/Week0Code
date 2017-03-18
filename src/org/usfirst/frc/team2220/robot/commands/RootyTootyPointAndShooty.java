package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RootyTootyPointAndShooty extends Command {

	double meterPower = -1.0;
	double shooterPower = 0.8;
	Timer startTimer;
	double highRumble = 1.0;
	double lowRumble = 0.25;
    public RootyTootyPointAndShooty() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.flamethrower);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTimer = new Timer();
    	startTimer.reset();
    	startTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.flamethrower.runShooter(shooterPower);
    	if(startTimer.get() > 1) //arbitrary
    	{
    		RobotMap.flamethrower.runMeter(meterPower);
    		OI.manipulatorStick.setRumble(RumbleType.kRightRumble, highRumble);
	    	OI.manipulatorStick.setRumble(RumbleType.kLeftRumble, highRumble);
    	}
    	else
    	{
    		RobotMap.flamethrower.stopMeter();
	    	OI.manipulatorStick.setRumble(RumbleType.kRightRumble, lowRumble);
	    	OI.manipulatorStick.setRumble(RumbleType.kLeftRumble, lowRumble);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
