
package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.*;
import org.usfirst.frc.team2220.robot.subsystems.Climber;
import org.usfirst.frc.team2220.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Central robot processing
 */
public class Robot extends IterativeRobot
{

	SendableChooser<Command> autoChooser;
	Command autoCommand;
	int printCount = 0;

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
		autoChooser.addDefault("do nothing", new Delay(10));
		autoChooser.addObject("Center Gear", new AutoCenterGear());
		autoChooser.addObject("Left Gear", new AutoLeftGear());
		autoChooser.addObject("Right Gear", new AutoRightGear());
		autoChooser.addObject("Red Shoot And Gear", new AutoRedShootAndGear());
		autoChooser.addObject("WR", new AutoDriveFromMidlineAndShoot());
		autoChooser.addObject("Baseline", new AutoBaseline());
		autoChooser.addObject("Blue Shoot", new AutoBlueShoot());
		autoChooser.addObject("Red Shoot", new AutoRedShoot());

		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit()
	{
		if (autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic()
	{
		if (autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll();

		// if autochooser isn't on dashboard, keep trying
		try
		{
			SmartDashboard.getData("Auto Chooser");
		}
		catch (Exception e)
		{
			if (autoChooser != null)
				SmartDashboard.putData("Auto Chooser", autoChooser);
		}
	}

	/**
	 * Selects command by chooser or manual
	 */
	@Override
	public void autonomousInit()
	{
		try
		{
			autoCommand = (Command) autoChooser.getSelected(); // chooser
			autoCommand.start();
		}
		catch (Exception e)
		{
		}
		// autoCommand = new AutoRightGear(); //manual
		//
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();

		updateSmartDashboard();
	}

	@Override
	public void teleopInit()
	{
		if (autoCommand != null)
			autoCommand.cancel();
		Scheduler.getInstance().removeAll(); // clears commands every time
												// teleop initialized
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();

		updateSmartDashboard();
	}

	public void updateSmartDashboard()
	{
		printCount++;
		if (printCount % 100 == 0)
		{
			SmartDashboard.putBoolean("DrivetrainGear", TankDrive.getInstance().getShiftState());
			SmartDashboard.putBoolean("CollectorGear", Climber.getInstance().getShiftState());
			SmartDashboard.putNumber("rEnc", TankDrive.getInstance().getRPosition());
			SmartDashboard.putNumber("lEnc", TankDrive.getInstance().getLPosition());
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
