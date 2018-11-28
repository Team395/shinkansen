/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Intake.AutomaticIntake;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
    Spark leftIntake = new Spark(RobotMap.Intake.leftIntake);
    Spark rightIntake = new Spark(RobotMap.Intake.rightIntake);
    Solenoid onSolenoid = new Solenoid(RobotMap.Intake.onSolenoid);
    Solenoid offSolenoid = new Solenoid(RobotMap.Intake.offSolenoid);
    DigitalInput cubeDetector = new DigitalInput(RobotMap.Intake.cubeDetector);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Intake() {
    rightIntake.setInverted(true);
  }

  public void IntakeOpen() {
    onSolenoid.set(true);
    offSolenoid.set(false);

  }

  public void setWheelSpeed(double speed) {
    leftIntake.set(speed);
    rightIntake.set(speed);

  }
  
  public void IntakeClose() {
    onSolenoid.set(false);
    offSolenoid.set(true);

  }
  
  public boolean IntakeIsOpen() {
    return onSolenoid.get();

  }

  public boolean IntakeIsClose() {
    return offSolenoid.get();

  }

  public boolean cubeInIntake() {
    return cubeDetector.get();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new AutomaticIntake());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
