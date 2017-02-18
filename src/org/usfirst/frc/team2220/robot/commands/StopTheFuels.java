package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.OI;
import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopTheFuels extends Command {

	boolean done = false;
    public StopTheFuels() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.flamethrower);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.flamethrower.stopMeter();
    	RobotMap.flamethrower.stopShooter();
    	OI.manipulatorStick.setRumble(RumbleType.kRightRumble, 0.0);
    	OI.manipulatorStick.setRumble(RumbleType.kLeftRumble, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
