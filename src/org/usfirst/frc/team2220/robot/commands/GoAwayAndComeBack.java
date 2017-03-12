package org.usfirst.frc.team2220.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives 16 feet forward, turns 180, drivers 16 feet back, turns 180
 */
public class GoAwayAndComeBack extends CommandGroup {

    public GoAwayAndComeBack() {
    	double driveDist = 28;
    	double driveSpeed = 1000;
    	double driveAccel = 500;
    	double turnSpeed = 600;
    	double turnAccel = 600;
    	addSequential(new AutoMotion(driveDist, driveDist, driveAccel, driveSpeed, 5));
    	//addSequential(new CurveMotion(360, 4, false, 0.5, 3));
    	addSequential(new AutoMotion(14.5, -14.5, turnAccel, turnSpeed, 5));
    	addSequential(new AutoShoot(6));
    	/*
    	addSequential(new AutoMotion(7.25, -7.25, 400, 750));
    	addSequential(new AutoMotion(7.25, -7.25, 400, 750));
    	*/
    	addSequential(new AutoMotion(driveDist, driveDist, driveAccel, driveSpeed, 5));
    	addSequential(new AutoMotion(14.5, -14.5, turnAccel, turnSpeed, 5));
    //	addSequential(new AutoMotion(7.25, -7.25, 100, 500));
    //	addSequential(new AutoMotion(7.25, -7.25, 100, 500));
    	
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
