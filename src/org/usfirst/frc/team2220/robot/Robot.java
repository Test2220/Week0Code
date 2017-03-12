
package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.*;
import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	SendableChooser<Command> autoChooser;
	CommandGroup autoCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		RobotMap.init();
		OI.init();
		
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Center Gear", new AutoCenterGear());
		autoChooser.addObject("Left Gear", new AutoLeftGear());
		autoChooser.addObject("Right Gear", new AutoRightGear());
		autoChooser.addObject("Blue Hopper", new AutoBlueHopper());
		autoChooser.addObject("Blue Shoot'n'Gear", new AutoBlueShootAndGear());
		
		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() 
	{
		if(autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
		if(autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll();
		
		try
		{
			SmartDashboard.getData("Auto Chooser");
		}
		catch(Exception e)
		{
			if(autoChooser != null)
				SmartDashboard.putData("Auto Chooser", autoChooser);
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() 
	{
		//autoCommand = (CommandGroup) autoChooser.getSelected();
		autoCommand = new AutoRightGear();
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() 
	{
		if(autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll(); //clears commands every time teleop initialized
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		updateSmartDashboard();
	}
	
	public void updateSmartDashboard()
	{
		//SmartDashboard.putNumber("scale", RobotMap.drive.getScale());
		//SmartDashboard.putBoolean("compressorOn", RobotMap.compressor.enabled());
		SmartDashboard.putNumber("rSetpoint", TankDrive.rDriveMotorSetpoint);
		SmartDashboard.putNumber("rActual", RobotMap.rDriveMaster.getPosition());
		SmartDashboard.putNumber("rPower", RobotMap.rDriveMaster.getOutputVoltage());
		SmartDashboard.putNumber("rError", RobotMap.rDriveMaster.getClosedLoopError());
		SmartDashboard.putNumber("rEnc", RobotMap.rDriveMaster.getEncPosition());
		
		SmartDashboard.putNumber("lSetpoint", TankDrive.lDriveMotorSetpoint);
		SmartDashboard.putNumber("lActual", RobotMap.lDriveMaster.getPosition());
		SmartDashboard.putNumber("lPower", RobotMap.lDriveMaster.getOutputVoltage());
		SmartDashboard.putNumber("lError", RobotMap.lDriveMaster.getClosedLoopError());
		SmartDashboard.putNumber("lEnc", RobotMap.lDriveMaster.getEncPosition());
		//SmartDashboard.putBoolean("aButton", OI.aButton.get());
		
		SmartDashboard.putNumber("rCruise", RobotMap.rDriveMaster.getMotionMagicCruiseVelocity());
		SmartDashboard.putNumber("rAccel", RobotMap.rDriveMaster.getMotionMagicAcceleration());
		
		SmartDashboard.putNumber("lCruise", RobotMap.lDriveMaster.getMotionMagicCruiseVelocity());
		SmartDashboard.putNumber("lAccel", RobotMap.lDriveMaster.getMotionMagicAcceleration());
		
		SmartDashboard.putString("Command", Scheduler.getInstance().toString());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
