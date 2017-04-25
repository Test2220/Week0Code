package lib.util;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CameraProcessor
{
	ArrayList<Contour> contours = new ArrayList<Contour>();
	private final double HALF_CAMERA_WIDTH = 160;
	private double distanceToTurn;
	
	public CameraProcessor()
	{
		NetworkTable contourTable = NetworkTable.getTable("GRIP/myContoursReport");
		double[] centerX = contourTable.getNumberArray("centerX", new double[] {});
		double[] area = contourTable.getNumberArray("area", new double[] {});
		for(int i = 0;i < centerX.length && i < area.length;i++)
		{
			contours.add(new Contour(centerX[i], area[i]));
		}
		
		Collections.sort(contours, (o1, o2) -> Double.compare(o2.getArea(), o1.getArea()));
		
		double pixelDistance, cameraError;
		try
		{
			Contour biggest = contours.get(0);
			Contour next = contours.get(1);
			pixelDistance = Math.abs(biggest.getCenterX() - next.getCenterX()); //8 inches
			cameraError = HALF_CAMERA_WIDTH - ((biggest.getCenterX() + next.getCenterX()) / 2);
		}
		catch(Exception e) //one or less targets
		{
			try
			{
				Contour biggest = contours.get(0);
				pixelDistance = 10; //TODO decide intelligent value
				cameraError = HALF_CAMERA_WIDTH - biggest.getCenterX();
			}
			catch(Exception e1) //no targets
			{
				distanceToTurn = 0;
				return;
			}
		}
		//regression equation
		//double distanceAway = (Math.log(pixelDistance) * -44.61) + 241.3; //logrithmic
		double distanceAway = (3262.49638 / pixelDistance) + -0.1697009; //inverse
		double inchesPerPixel = 8 / pixelDistance;
		
		double inchesToRotate = inchesPerPixel * cameraError;
		double degreesToRotate = (inchesToRotate / (distanceAway * 2 * Math.PI)) * 360;
		double encRotationsPerDegree = 7.5 / 90; //7.25 = 90 degrees
		
		distanceToTurn = degreesToRotate * encRotationsPerDegree;
	}
	
	public double getDistanceToTurn()
	{
		return distanceToTurn;
	}
	

	public String toString()
	{
		String out = ">>>[Contours]<<<";
		for (int i = 0; i < contours.size(); i++)
		{
			out += "\n" + contours.get(i).toString();
		}
		out += "\n>>>>>>>><<<<<<<<";
		return out;
	}
}
