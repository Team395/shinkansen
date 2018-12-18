/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final String Elevator = null;
// For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  public static int leftStick = 0;
  public static int rightStick = 1;
  public static int xboxController = 2;

  public static class Intake {
    public static int leftIntake = 0;
    public static int rightIntake = 1;
    public static int onSolenoid = 0;
    public static int offSolenoid = 1;
    public static int cubeDetector = 9;
  }

  public static class Elevator {
      public static int winchControllerTalon = 5;
      public static int bottomLimitSwitch = 5;  
  }

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
