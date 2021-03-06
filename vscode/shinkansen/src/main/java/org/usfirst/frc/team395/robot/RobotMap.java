/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public abstract class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static int leftStick = 0;
	public static int rightStick = 1;
	public static int xboxController = 2;
	
	public static class Drive {
		public static int leftLeaderTalon = 1;
		public static int rightLeaderTalon = 3;
		public static int leftFollowerTalon = 2;
		public static int rightFollowerTalon = 4;
	}
	
	public static class Sensors {
		public static int leftEncoderTalon = 1;
		public static int rightEncoderTalon = 3;
		public static int gyroTalon = 5;
		public static int winchEncoderTalon = 2;
	}
	
	public static class Elevator {
		public static int winchControllerTalon = 5;
		public static int bottomLimitSwitch = 5;
	}

	public static class Intake {
		public static int leftIntake = 0;
		public static int rightIntake = 1;
		public static int openSolenoid = 0;
		public static int closeSolenoid = 1;
		public static int cubeDetectedSensor = 9;
	}
}
