/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class AutoCenterCube extends Command {

  private Timer timer = new Timer();
  private static final double CENTER_TIME = 0.25;
  
  public AutoCenterCube() {
      super("AutoCenterCube");
      requires(Robot.intake);
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
  }
  
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      timer.reset();
      timer.start();
      Robot.intake.setIntakeSpeed(-1);
  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return timer.get() > CENTER_TIME || !Robot.intake.isCubeInIntake();
  }
  
  // Called once after isFinished returns true
  @Override
  protected void end() {
      if(!Robot.intake.isCubeInIntake()) {
          Scheduler.getInstance().add(new AutomaticIntake());
      }
  }
  
  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
