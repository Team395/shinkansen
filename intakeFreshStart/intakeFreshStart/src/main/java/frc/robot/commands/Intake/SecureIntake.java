/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

/**
 * Reset then start the timer, then close the claw and spin wheels inward.
 *  Then if cube is not in intake then reset automatic intake again or if
 *  after 1 second the cube is still in the claw then switch to manual.
 */
public class SecureIntake extends Command {
  private Timer timer = new Timer();
  
  public SecureIntake() {
    super("SecureIntake");
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   Robot.intake.IntakeClose();

   Robot.intake.setWheelSpeed(-1);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(1) || !Robot.intake.cubeInIntake();  
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(!Robot.intake.cubeInIntake()) {
      Scheduler.getInstance().add(new AutomaticIntake());
    }
    Robot.intake.setWheelSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
