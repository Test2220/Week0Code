package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;
import org.usfirst.frc.team2220.robot.commands.RunIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void run(double val)
	{
		RobotMap.collectorMaster.set(val);
	}
	
	public void shift(boolean highGear)
	{
		if(highGear)
		{
			RobotMap.collectorShifter.set(Value.kForward);
		}
		else
		{
			RobotMap.collectorShifter.set(Value.kReverse);
		}
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new RunIntake(0.0));
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

