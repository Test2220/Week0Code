package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftCollectorON extends InstantCommand {

	public ShiftCollectorON() {
        super();
        requires(RobotMap.intake);
    }

    // Called once when the command executes
    protected void initialize() 
    {
    	RobotMap.intake.shift(true);
    }
}
