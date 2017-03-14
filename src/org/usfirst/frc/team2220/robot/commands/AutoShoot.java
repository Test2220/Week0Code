package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoShoot extends TimedCommand {

	double meterPower = -1.0;
	double shooterPower = 0.7;
	Timer startTimer;
	
	public AutoShoot(double timeout) {
        super(timeout);
        requires(RobotMap.flamethrower);
        requires(RobotMap.washerSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	startTimer = new Timer();
    	startTimer.reset();
    	startTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	RobotMap.flamethrower.runShooter(shooterPower);
    	if(startTimer.get() > 0.5) //arbitrary
    	{
    		RobotMap.flamethrower.runMeter(meterPower);
    		RobotMap.washerSubsystem.runWasher(1.0);
    	}
    	else
    	{
    		RobotMap.flamethrower.stopMeter();
    		RobotMap.washerSubsystem.runWasher(0.0);
    	}
    }

    // Called once after timeout
    protected void end() 
    {
    	RobotMap.flamethrower.stopMeter();
    	RobotMap.flamethrower.stopShooter();
		RobotMap.washerSubsystem.runWasher(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
