package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueShootAndGear extends CommandGroup {

	double driveSpeed = 1000;
	double driveAccel = 500;
	double turnSpeed = 600;
	double turnAccel = 600;
	double distance = 38;
	
    public AutoBlueShootAndGear() {
    	//Shoot, turn and cross line
    	addSequential(new AutoShoot(6));
    	addSequential(new AutoMotion(7.25, -7.25, turnAccel, turnSpeed, 4));
    	addSequential(new AutoMotion(distance, distance, driveAccel, driveSpeed, 5));
    	
    	//turn to gear, line up and drive forward
    	addSequential(new AutoMotion(-4.8, 4.8, turnAccel, turnSpeed, 5));
    	addSequential(new FBCameraMotion(2));
    	addSequential(new AutoMotion(4, 4, driveAccel, driveSpeed, 2));
    	
    	//release
    	addSequential(new ShiftCollector(false));
    	addSequential(new Delay(3));
    	addSequential(new ShiftCollector(true));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
