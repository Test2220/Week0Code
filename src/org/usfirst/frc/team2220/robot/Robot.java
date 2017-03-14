
package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Central robot processing
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
		
		//if autochooser isn't on dashboard, keep trying
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
	 * Selects command by chooser or manual
	 */
	@Override
	public void autonomousInit() 
	{
		//autoCommand = (CommandGroup) autoChooser.getSelected(); //chooser
		autoCommand = new AutoRightGear(); //manual
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
		SmartDashboard.putBoolean("DrivetrainGear", RobotMap.driveInHighGear);
		SmartDashboard.putBoolean("CollectorGear",  RobotMap.collectorInHighGear);
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
