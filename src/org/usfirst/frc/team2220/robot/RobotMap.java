package org.usfirst.frc.team2220.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{

	public static CANTalon rDriveMaster, rDriveSlave;
	public static CANTalon lDriveMaster, lDriveSlave;
	public static CANTalon climberMaster, climberSlave;
	public static CANTalon washer;
	public static CANTalon metererer;
	public static CANTalon shooter;
	public static CANTalon gearIntakeR, gearIntakeL;

	public static DoubleSolenoid driveShifter;
	public static DoubleSolenoid collectorShifter;

	public static Compressor compressor;

	public final static int CLOSEDLOOPERROR = 30; //this is in native units so inchesToEncRev doesn't apply? TODO

	public static double deadzone(double val, double zone)
	{
		if (val < zone && val > -zone)
			return 0;
		return val;
	}

	public static double inchesToEncRot(double input)
	{
		input = input / 23.75; // wheel rotations
		input = input * 8; // encoder revolutions
		return input;
	}

	public static double feetToEncRot(double input)
	{
		return inchesToEncRot(input / 12);
	}

	public static void init()
	{
		CameraServer.getInstance().startAutomaticCapture();

		//@formatter:off -> nice indentation makes ez to ready
		lDriveMaster 	= new CANTalon(1);
		lDriveSlave 	= new CANTalon(2);
		rDriveMaster 	= new CANTalon(3);
		rDriveSlave 	= new CANTalon(4);

		climberMaster 	= new CANTalon(5);
		climberSlave 	= new CANTalon(6);
		washer 			= new CANTalon(8);
		metererer 		= new CANTalon(7);
		shooter 		= new CANTalon(9);
		gearIntakeR 	= new CANTalon(10);
		gearIntakeL 	= new CANTalon(11);
		//@formatter:on

		compressor = new Compressor();

		// this may be wrong on actual robot
		driveShifter = new DoubleSolenoid(6, 7);
		collectorShifter = new DoubleSolenoid(0, 1);

		compressor = new Compressor();

		// TODO test before running
		lDriveSlave.changeControlMode(TalonControlMode.Follower);
		lDriveSlave.set(lDriveMaster.getDeviceID());

		rDriveSlave.changeControlMode(TalonControlMode.Follower);
		rDriveSlave.set(rDriveMaster.getDeviceID());

		climberSlave.changeControlMode(TalonControlMode.Follower);
		climberSlave.set(climberMaster.getDeviceID());

		/* Encoders = 128 ticks per rev Talon Native Units (allowableError) =
		 * 512 per rev Encoder Output 12:36 = 3x speed Encoder = 384 actual tick
		 * per wheel rotation Talon Native Unites = 1536 per wheel rotation
		 * 8inch wheel ~25 inch cir 25:1536 if Closed loopError = 61.44 our
		 * range = +- 0.5 inches 15 position units per rotation? 4Talon native
		 * units ~= 1 degree */
		/* input -> rotations 1 wheel rotation = 8 encoder revs wheel distance =
		 * 2.25 feet or 27 inches ~2 feet/wheel rotation */

		// TODO remove
		int accel = 400;
		int cruiseVel = 400;

		rDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rDriveMaster.reverseSensor(true);
		rDriveMaster.configEncoderCodesPerRev(256); // 4 x 256 = 1024
													// wrong->4x128=512
		rDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

		rDriveMaster.setEncPosition(0);

		double kP = 2.0;
		double kI = 0.0015;
		double kD = 0.0; // 50
		rDriveMaster.setF(240);// 119.47); //encoder ticks per 100ms -> 9.34 RPS
		rDriveMaster.setPID(kP, kI, kD); // i->0.001 //p->2.4
		rDriveMaster.setMotionMagicAcceleration(accel); // RPM/S
		rDriveMaster.setMotionMagicCruiseVelocity(cruiseVel); // RPM

		lDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		lDriveMaster.reverseSensor(false);
		lDriveMaster.configEncoderCodesPerRev(256);
		lDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

		lDriveMaster.setEncPosition(0);

		lDriveMaster.setF(240);// 119.47); //encoder ticks per 100ms -> 9.34 RPS
		lDriveMaster.setPID(kP, kI, kD); // i->0.001 //p->2.4
		lDriveMaster.setMotionMagicAcceleration(accel); // RPM/S
		lDriveMaster.setMotionMagicCruiseVelocity(cruiseVel); // RPM

	}
}
