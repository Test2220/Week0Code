package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CloseCLAWZ extends InstantCommand {

	 public CloseCLAWZ() {
	        super();
	        requires(RobotMap.clawz);
	    }

	    // Called once when the command executes
	    protected void initialize() 
	    {
	    	RobotMap.clawz.closeClawz();
	    }


}
