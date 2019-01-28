/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Intake2019Command;

/**
 * Add your docs here.
 */
public class Intake2019 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark outerRoller = new Spark(1);
  Spark innerRoller = new Spark(0);


  public void setOuterRoller(double speed) {
    outerRoller.set(0.75 * speed);
  }

  public void setInnerRoller(double speed) {
    innerRoller.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Intake2019Command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
