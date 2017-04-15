package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeccelerateClimber extends Command {

    public DeccelerateClimber() {
        requires(Climber.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double currentVal = RobotMap.deadzone( Climber.getInstance().getRunVal(), 0.15 );
    	double newVal = 0;
    	double changeVal = 0.01;
    	if(currentVal == 0)
    	{
    		Climber.getInstance().run(0.0);
    		return;
    	}
    	if(currentVal > 0)
    	{
    		newVal = currentVal - changeVal;
    	}
    	else if (currentVal < 0)
    	{
    		newVal = currentVal + changeVal;
    	}
    	/*
    	double newVal = currentVal * 0.95;
    	newVal = RobotMap.deadzone(newVal, 0.15);
    	*/
    	Climber.getInstance().run(newVal);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }
}
