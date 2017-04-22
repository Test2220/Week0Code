package lib.util;

public class Target
{
	private double position;
	private double camErr;

	public Target(double pos, double err)
	{
		position = pos;
		camErr = err;
	}

	public double getPosition()
	{
		return position;
	}

	public double getCameraError()
	{
		return camErr;
	}
}
