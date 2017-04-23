package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
		//System.out.println(contourTable);
		double[] centerX = contourTable.getNumberArray("centerX", new double[] {});
		
		//System.out.println(centerX[0]);
		double pegCenter;
		if (centerX.length == 0)
		{
			System.out.println("endThisNow flag set");
			endThisNow = true;
			return null;
		}
		if (centerX.length == 1)
		{
			pegCenter = centerX[0];
		}
		else
		{
			pegCenter = (centerX[0] + centerX[1]) / 2;
		}
		double camErr = HALF_CAMERA_WIDTH - pegCenter;
		if(Math.abs(camErr) < 5)
		{
			System.out.println("within error");
			endThisNow = true;
		}
		double position = TankDrive.getInstance().getLPosition();
		return new Target(position, camErr, Timer.getFPGATimestamp());
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
			try
			{
				Target x = getNewTarget();
				cameraProcessor = new CameraProcessor(x);
				System.out.println(x);
			}
			catch (Exception e){}
		//cameraProcessor.resetMotorVal();
		//rightMotorInitial = TankDrive.getInstance().getRPosition();
		leftMotorInitial = TankDrive.getInstance().getLPosition();
		TankDrive.getInstance().changeToMotionMagic();
		TankDrive.getInstance().setBothAccel(600);
		TankDrive.getInstance().setBothCruiseVel(600);
		
		//TankDrive.getInstance().setRPosition(rightMotorInitial + cameraProcessor.getMotorVal());
		TankDrive.getInstance().setLPosition(leftMotorInitial + cameraProcessor.getMotorVal());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if((TankDrive.getInstance().hasHitRSetpoint() && TankDrive.getInstance().hasHitLSetpoint()))
		{
			cameraProcessor.resetMotorVal(getNewTarget());
		}
		try
		{
			double motorVal = cameraProcessor.addTarget(getNewTarget());
			//System.out.println(cameraProcessor);

			//TankDrive.getInstance().setRPosition(rightMotorInitial + motorVal);
			TankDrive.getInstance().setLPosition(leftMotorInitial + motorVal);
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println("it done broke boiiiii");
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		boolean potatoes = endThisNow;// || (TankDrive.getInstance().hasHitRSetpoint() && TankDrive.getInstance().hasHitLSetpoint());
		//System.out.println("isFinished = " + potatoes);
		return potatoes;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		System.out.println("command done");
		endThisNow = false;
		Scheduler.getInstance().add(new DriveOff());
	}
}
