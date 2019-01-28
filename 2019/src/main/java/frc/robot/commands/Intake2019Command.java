/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Intake2019Command extends Command {
  public Intake2019Command() {
    requires(Robot.intake2019);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intake2019.setOuterRoller(Robot.oi.getIntakeThrottle());
    Robot.intake2019.setInnerRoller(Math.max(0, Robot.oi.getIntakeThrottle())) ;
    // if(Robot.oi.getInnerRollerIn()) 
    //   Robot.intake2019.setInnerRoller(1);
    // else if(Robot.oi.getInnerRollerOut())
    //   Robot.intake2019.setInnerRoller(-1);
    // else 
    //   Robot.intake2019.setInnerRoller(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
