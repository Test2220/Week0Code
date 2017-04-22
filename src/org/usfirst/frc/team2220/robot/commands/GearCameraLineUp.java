package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import lib.util.CameraProcessor;
import lib.util.Target;

/**
 *
 */
public class GearCameraLineUp extends Command
{
	CameraProcessor cameraProcessor;
	NetworkTable contourTable;
	boolean endThisNow = false;
	
	public double rightMotorInitial, leftMotorInitial;
	
	private final double HALF_CAMERA_WIDTH = 160;
	
	public GearCameraLineUp()
	{
		requires(TankDrive.getInstance());
	}

	public Target getNewTarget()
	{
		contourTable = NetworkTable.getTable("GRIP/myContoursReport");
		double[] centerX = contourTable.getNumberArray("centerX", new double[] {});
		double pegCenter;
		if(centerX.length == 0)
		{
			endThisNow = true;
			return null;
		}
		if(centerX.length == 1)
		{
			pegCenter = centerX[0];
		}
		else
		{
			pegCenter = (centerX[0] + centerX[1]) / 2;
		}
		double camErr = HALF_CAMERA_WIDTH - pegCenter;
		double position = TankDrive.getInstance().getRPosition();
		return new Target(position, camErr);
	}
	// Called just before this Command runs the first time
	protected void initialize()
	{
		Target initialTarget = getNewTarget();
		if(initialTarget != null)
		{
			cameraProcessor = new CameraProcessor(getNewTarget());
		}
		rightMotorInitial = TankDrive.getInstance().getRPosition();
		leftMotorInitial = TankDrive.getInstance().getLPosition();
		TankDrive.getInstance().changeToMotionMagic();
		TankDrive.getInstance().setBothAccel(100);
		TankDrive.getInstance().setBothCruiseVel(200);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double motorVal = cameraProcessor.addTarget(getNewTarget());
		
		TankDrive.getInstance().setRPosition(rightMotorInitial + motorVal);
		TankDrive.getInstance().setLPosition(leftMotorInitial - motorVal);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return endThisNow || (TankDrive.getInstance().hasHitRSetpoint() && TankDrive.getInstance().hasHitLSetpoint());
	}

	// Called once after isFinished returns true
	protected void end()
	{
		new DriveOff();
	}
}
