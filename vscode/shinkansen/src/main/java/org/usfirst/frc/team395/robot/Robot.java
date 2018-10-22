/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//TODO: Uncomment
// import org.usfirst.frc.team395.robot.commandgroups.Drive10Turn90Drive5;
import org.usfirst.frc.team395.robot.commands.*;
import org.usfirst.frc.team395.robot.commands.intake.*;
import org.usfirst.frc.team395.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static ExampleSubsystem subsystem = new ExampleSubsystem();
	
	public static TalonMap talonMap = new TalonMap();
    public static Intake intake = new Intake();
    
	public static Compressor compressor = new Compressor();

	public static Elevator elevator = new Elevator();
	public static DrivetrainEncoders drivetrainEncoders = new DrivetrainEncoders();
	//TODO: Uncomment
	// public static DrivetrainGyro drivetrainGyro = new DrivetrainGyro();
	
	public static Drivetrain drivetrain = new Drivetrain();
	public static OI oi = new OI();
	
	Command autonomousCommand;
	// SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		compressor.setClosedLoopControl(true);
		oi.setUpTriggers();
		Scheduler.getInstance().add((intake.isCubeInIntake() ? new ManualIntake() : new AutomaticIntake()));
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// // chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		//autonomousCommand = new Drive10Turn90Drive5();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		elevator.initializeSystem();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		elevator.initializeSystem();
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
3	 */
	double setpointInches = 0;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Elevator Trigger", oi.elevatorTrigger.get());
		SmartDashboard.putString("Elevator Command", elevator.getCurrentCommandName());
		SmartDashboard.putString("Intake Command", intake.getCurrentCommandName());
		SmartDashboard.putString("Drivetrain Command", drivetrain.getCurrentCommandName());
		SmartDashboard.putBoolean("Cube In Intake", intake.isCubeInIntake());
		// System.out.println("Manual Trigger " + (oi.manualTrigger.get() ? "true" : "false"));
		// System.out.println("Automatic Trigger " + (oi.automaticTrigger.get() ? "true" : "false"));
		// System.out.println("Retain Trigger " + (oi.retainTrigger.get() ? "true" : "false"));
		// System.out.println("Threshold Trigger " + (oi.thresholdTrigger.get() ? "true\n" : "false\n"));

	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

