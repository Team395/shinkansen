/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX leftLeader = new WPI_TalonSRX(RobotMap.Drive.leftLeaderTalon);
  WPI_TalonSRX leftFollower = new WPI_TalonSRX(RobotMap.Drive.leftFollowerTalon);
  WPI_TalonSRX rightLeader = new WPI_TalonSRX(RobotMap.Drive.rightLeaderTalon);
  WPI_TalonSRX rightFollower = new WPI_TalonSRX(RobotMap.Drive.rightFollowerTalon);

  SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftLeader, leftFollower);
  SpeedControllerGroup rightGroup =new SpeedControllerGroup(rightLeader, rightFollower);

  public void tankDrive(double left, double right) {
    leftGroup.set(left);
    rightGroup.set(right);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
