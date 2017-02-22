package org.usfirst.frc.team2220.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import org.usfirst.frc.team2220.robot.subsystems.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static CANTalon rDriveMaster, rDriveSlave;  			
	public static CANTalon lDriveMaster, lDriveSlave;			
	public static CANTalon collectorMaster, collectorSlave;		
	public static CANTalon washer;								
	public static CANTalon metererer;							
	public static CANTalon shooter;						
	
	public static DoubleSolenoid driveShifter;
	public static DoubleSolenoid collectorShifter;
	public static DoubleSolenoid clawzPiston;
	
	public static PowerDistributionPanel panel;
	
	public static TankDrive drive;
	public static FlameThrower flamethrower;
	public static Washer washerSubsystem;
	public static Intake intake;
	public static CLAWZ clawz;
	
	public static boolean isInHighGear;
	
	public static Compressor compressor;
	
	
	
	public static double deadzone(double val, double zone)
	{
		if(val < zone && val > -zone)
			return 0;
		return val;
	}
	
	public final static int CLOSEDLOOPERROR = 15; //a little less than half an inch or +- 2 degrees
	
	public static void init()
	{
		CameraServer.getInstance().startAutomaticCapture();
		
		lDriveMaster 		= new CANTalon(1);
		lDriveSlave 		= new CANTalon(2);
		rDriveMaster 		= new CANTalon(3);
		rDriveSlave  		= new CANTalon(4);
		
	 	collectorMaster 	= new CANTalon(5); //5
		collectorSlave		= new CANTalon(6); //6
		washer				= new CANTalon(8);
		metererer			= new CANTalon(7);
		shooter 		= new CANTalon(9);
		
		panel = new PowerDistributionPanel();
		
		
		intake = new Intake();
		
		washerSubsystem = new Washer();
		
		clawz = new CLAWZ();
		
		compressor = new Compressor();
		
		
		driveShifter = new DoubleSolenoid(0, 3);
		collectorShifter = new DoubleSolenoid(6, 7);
		clawzPiston = new DoubleSolenoid(1, 2);
		//6 and 7?
		
		drive = new TankDrive();
		flamethrower = new FlameThrower();
	
		compressor = new Compressor();
		
		isInHighGear = false;
		
		//TODO test before running
		lDriveSlave.changeControlMode(TalonControlMode.Follower);
		lDriveSlave.set(lDriveMaster.getDeviceID());
		
		rDriveSlave.changeControlMode(TalonControlMode.Follower);
		rDriveSlave.set(rDriveMaster.getDeviceID());
		
		collectorSlave.changeControlMode(TalonControlMode.Follower);
		collectorSlave.set(collectorMaster.getDeviceID());
		
		/*
		 * Encoders = 							 128 ticks per rev
		 * Talon Native Units (allowableError) = 512 per rev
		 * Encoder Output 12:36 = 3x speed
		 * Encoder = 384 actual tick per wheel rotation
		 * Talon Native Unites = 1536 per wheel rotation
		 * 8inch wheel
		 * ~25 inch cir
		 * 25:1536
		 * 
		 * if Closed loopError = 61.44 our range = +- 0.5 inches
		 * 
		 * 
		 * 4Talon native units ~= 1 degree
		 * 
		 */
		
		
		
		
		rDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rDriveMaster.reverseSensor(true);
		rDriveMaster.configEncoderCodesPerRev(128); //4x128=512
		rDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);
		
		rDriveMaster.setEncPosition(0);

		rDriveMaster.setF(119.47); //encoder ticks per 100ms -> 9.34 RPS
		rDriveMaster.setPID(2.0, 0, 50); //i->0.001 //p->2.4
		rDriveMaster.setMotionMagicAcceleration(1000);   //RPM/S
		rDriveMaster.setMotionMagicCruiseVelocity(500); //RPM
		
		
		
		lDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		lDriveMaster.reverseSensor(true);
		lDriveMaster.configEncoderCodesPerRev(128);
		lDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);
		
		lDriveMaster.setEncPosition(0);

		lDriveMaster.setF(119.47); //encoder ticks per 100ms -> 9.34 RPS
		lDriveMaster.setPID(2.0, 0, 50); //i->0.001 //p->2.4
		lDriveMaster.setMotionMagicAcceleration(1000);   //RPM/S
		lDriveMaster.setMotionMagicCruiseVelocity(500); //RPM
		
	
		
	}
}
