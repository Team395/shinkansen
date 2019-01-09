/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnDegrees extends Command {
	
	PIDController turnPID = new PIDController(0.04, 0, 0.004, 0, Robot.drivetrainGyro, Robot.drivetrain);
	double turnDegrees = 0.0;
	double ACCEPTABLE_ERROR_DEGREES = 1.5;

    public TurnDegrees(double turnDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       	requires(Robot.drivetrain);
    	setInterruptible(false);
 
    	this.turnDegrees = turnDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnPID.setSetpoint(Robot.drivetrainGyro.getAngleZ() + turnDegrees);
    	turnPID.setAbsoluteTolerance(ACCEPTABLE_ERROR_DEGREES);
    	turnPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    	turnPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
