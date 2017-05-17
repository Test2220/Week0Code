package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap
{

	//@formatter:off
	public static final int LEFT_DRIVE_MASTER  = 1,
							LEFT_DRIVE_SLAVE   = 2,
							RIGHT_DRIVE_MASTER = 3,
							RIGHT_DRIVE_SLAVE  = 4,
							CLIMBER_MASTER     = 5,
							CLIMBER_SLAVE      = 6,
							METERERER		   = 7,
							WASHER			   = 8,
							SHOOTER			   = 9,
							RIGHT_GEAR_INTAKE  = 10,
							LEFT_GEAR_INTAKE   = 11;								
	//@formatter:on
	public static DoubleSolenoid driveShifter;
	public static DoubleSolenoid collectorShifter;

	public static Compressor compressor;

	public static double deadzone(double val, double zone)
	{
		if (val < zone && val > -zone)
			return 0;
		return val;
	}

	public static double inchesToEncRot(double input)
	{
		input = input / 23.75; 	//Wheel rotations
		input = input * 8; 		//Encoder revolutions
		return input;
	}

	public static double feetToEncRot(double input)
	{
		return inchesToEncRot(input * 12);
	}

	public static void init()
	{
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture();
		compressor = new Compressor();

		//Competition
		driveShifter = new DoubleSolenoid(6, 7); 
		collectorShifter = new DoubleSolenoid(0, 1); 
		
		//Practice
		/*
		driveShifter = new DoubleSolenoid(0, 3);
		collectorShifter = new DoubleSolenoid(6, 7);
		*/
	}
}
