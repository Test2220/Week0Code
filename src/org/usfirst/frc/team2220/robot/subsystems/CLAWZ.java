package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CLAWZ extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void openClawz()
	{
		RobotMap.clawzPiston.set(Value.kForward);
	}
	
	public void closeClawz()
	{
		RobotMap.clawzPiston.set(Value.kReverse);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

