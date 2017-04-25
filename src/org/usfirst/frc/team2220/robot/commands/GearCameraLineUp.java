package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.command.Command;
import lib.util.CameraProcessor;

/**
 *
 */
public class GearCameraLineUp extends Command
{
	CameraProcessor cameraProcessor;

	public GearCameraLineUp()
	{
		requires(TankDrive.getInstance());
	}


	// Called just before this Command runs the first time
	protected void initialize()
	{
		cameraProcessor = new CameraProcessor();
		TankDrive.getInstance().changeToMotionMagic();
		TankDrive.getInstance().resetEncoderPos();
		TankDrive.getInstance().setBothAccel(500);
		TankDrive.getInstance().setBothCruiseVel(1000);
		
		System.out.println(cameraProcessor);
		
		double turnVal = cameraProcessor.getDistanceToTurn();
		System.out.println("TurnVal = " + turnVal);
		TankDrive.getInstance().incrementRPosition(turnVal);
		TankDrive.getInstance().incrementLPosition(-turnVal);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return TankDrive.getInstance().hasHitRSetpoint() && TankDrive.getInstance().hasHitLSetpoint(); //TODO why does this exit early?
	}

	// Called once after isFinished returns true
	protected void end()
	{
		System.out.println("command done");
		//Scheduler.getInstance().add(new DriveOff());
	}
}
