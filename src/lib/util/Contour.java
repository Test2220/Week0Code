package lib.util;

public class Contour
{
	private double centerX;
	private double area;
	public Contour(double x, double a)
	{
		centerX = x;
		area = a;
	}
	
	public double getArea()
	{
		return area;
	}
	
	public double getCenterX()
	{
		return centerX;
	}
	
	public String toString()
	{
		return "CenterX = " + centerX + "\tArea = " + area;
	}
}
