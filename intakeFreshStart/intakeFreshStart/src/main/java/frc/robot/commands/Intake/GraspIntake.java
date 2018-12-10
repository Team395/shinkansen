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
 * After decide intake ends, begins a timer. The wheels begin to spin inward, 
 * after 0.5 seconds or the cube is not in the claw, then if the cube is not 
 * in claw, start from the beggining of automatic intake, else cloase the claw.
 */
public class GraspIntake extends Command {
    private Timer timer = new Timer();
    
 
    public GraspIntake() {
      super("GraspIntake");
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    //make sure to state if cubInIntake is true or false
    }
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {    
    Robot.intake.setWheelSpeed(-1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(0.5) || !Robot.intake.cubeInIntake();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(!Robot.intake.cubeInIntake()) {
      Scheduler.getInstance().add(new AutomaticIntake());
    }
    Robot.intake.IntakeClose();
    Robot.intake.setWheelSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
