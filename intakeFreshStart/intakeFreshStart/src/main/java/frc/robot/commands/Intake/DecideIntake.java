/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DecideIntake extends Command {
  public DecideIntake() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intake);
    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
   
    //going to start getting the input of the sensor and put condiionals for it later.
  }
  //while the sensor has an input of false, continue

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intake.setWheelSpeed(Robot.oi.getClawSpeed());
    Robot.intake.cubeInIntake();
    Robot.intake.IntakeOpen();
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.intake.cubeInIntake();
  //only one line
  // Called once after isFinished returns true
  }
  @Override
  protected void end() {
   //CALL CP|
   
   
    // if(Robot.intake.cubeInIntake()) {
     //GIVe power to graspIntake.java   
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
