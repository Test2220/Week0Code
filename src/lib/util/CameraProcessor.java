package lib.util;

import java.util.ArrayList;

import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

public class CameraProcessor
{
	ArrayList<Target> targets = new ArrayList<Target>();

	private double motorVal = 0.1; //TODO decide intelligent default

	public CameraProcessor(Target initialVal)
	{
		targets.add(initialVal);
		if(initialVal.getCameraError() > 0)
		{
			motorVal = 0.25;
		}
		else
		{
			motorVal = -0.25;
		}
	}
	
	public double getMotorVal()
	{
		return motorVal;
	}
	
	public void resetMotorVal(Target newFrame)
	{
		targets.add(newFrame);
		if(newFrame.getCameraError() > 0)
		{
			motorVal = 0.25;
		}
		else
		{
			motorVal = -0.25;
		}
	}
	

	public double addTarget(Target input)
	{
		while(targets.size() > 5)
		{
			targets.remove(0);
		}
		if (input != null && targets.get(targets.size() - 1).getCameraError() != input.getCameraError())
		{
			if(targets.get(targets.size() - 1).getPosition() != input.getPosition())
			{
				if( Math.abs(input.getCameraError() - targets.get(targets.size() - 1).getCameraError()) >= 10)
				{
					
					targets.add(input);
					
					//System.out.println(this);
					calculateNewPosition();
				}
			}
		}
		return motorVal;
	}

	private double calculateNewPosition()
	{
		if (targets.size() >= 2)
		{
			Target current = targets.get(targets.size() - 1);
			Target previous = targets.get(targets.size() - 2);
			double deltaCameraError = current.getCameraError() - previous.getCameraError();
			double deltaPosition = current.getPosition() - previous.getPosition();
			
			System.out.print("Old = " + motorVal);
			System.out.print(" Current = [" + current + "] deltaErr = " + deltaCameraError + " deltaPos = " + deltaPosition);
			motorVal = Math.abs(deltaPosition / deltaCameraError) * current.getCameraError();
			System.out.println(" New = " + motorVal);
		}
		return motorVal;
	}

	public String toString()
	{
		String out = ">>>[Targets]<<<";
		out += "\nMotorVal = " + motorVal;
		for (int i = 0; i < targets.size(); i++)
		{
			out += "\n" + targets.get(i).toString();
		}
		out += "\n>>>>>>>><<<<<<<<";
		return out;
	}
}
