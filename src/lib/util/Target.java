package lib.util;

public class Target
{
	private double position;
	private double camErr;
	private double timeIndex;

	public Target(double pos, double err, double time)
	{
		position = pos;
		camErr = err;
		timeIndex = time;
	}

	public double getPosition()
	{
		return position;
	}

	public double getCameraError()
	{
		return camErr;
	}
	
	public double getTime()
	{
		return timeIndex;
	}
	
	public String toString()
	{
		return "Pos = " + position + " Err = " + camErr + " Time = " + timeIndex;
	}
}
