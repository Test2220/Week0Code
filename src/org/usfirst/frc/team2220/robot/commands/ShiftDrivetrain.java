package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftDrivetrain extends InstantCommand {

	public boolean shiftVal = false;
    public ShiftDrivetrain(boolean shiftVal) 
    {
    	this.shiftVal = shiftVal;
    	System.out.println("shiftVal = " + shiftVal);
    	
    	
    }

    // Called after the command is started... every time.
    protected void initialize() 
    {
    	RobotMap.drive.shift(shiftVal);
    }
}
