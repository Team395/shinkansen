/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem {
  Talon leftIntake = new Talon(RobotMap.Intake.leftIntake);
  Talon rightIntake = new Talon(RobotMap.Intake.rightIntake);
  Solenoid openSolenoid = new Solenoid(RobotMap.Intake.openSolenoid);
  Solenoid closeSolenoid = new Solenoid(RobotMap.Intake.closeSolenoid);
  DigitalInput cubeDetectedSensor = new DigitalInput(RobotMap.Intake.cubeDetectedSensor);
  
  public Intake() {
      rightIntake.setInverted(true);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void setIntakeSpeed(double speed) {
      leftIntake.set(speed);
      rightIntake.set(speed);
  }

  public void openIntake() {
      openSolenoid.set(true);
      closeSolenoid.set(false);
  }

  public void closeIntake() {
      openSolenoid.set(false);
      closeSolenoid.set(true);
  }

  public boolean isIntakeOpen() {
      return openSolenoid.get();
  }

  public boolean isCubeInIntake() {
      return cubeDetectedSensor.get();
  }
  
  @Override
  public void initDefaultCommand() {
  // Set the default command for a subsystem here.
  // setDefaultCommand(new MySpecialCommand());
  }
}
