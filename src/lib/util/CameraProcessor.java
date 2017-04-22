package lib.util;

import java.util.ArrayList;

public class CameraProcessor
{
	ArrayList<Target> targets = new ArrayList<Target>();

	double motorVal = 10; //TODO decide intelligent default

	public CameraProcessor(Target initialVal)
	{
		targets.add(initialVal);
	}

	public double addTarget(Target input)
	{
		
		if(input != null && targets.get(targets.size() - 1).getCameraError() != input.getCameraError())
		{
			targets.add(input);
			calculateNewPosition();
		}
		return motorVal;
	}

	public double calculateNewPosition()
	{
		if (targets.size() >= 2)
		{
			Target current = targets.get(targets.size() - 1);
			Target previous = targets.get(targets.size() - 2);
			double deltaCameraError = current.getCameraError() - previous.getCameraError();
			double deltaPosition = current.getPosition() - previous.getPosition();

			motorVal = (deltaPosition / deltaCameraError) * current.getCameraError() * -1;
		}
		return motorVal;
	}
}
