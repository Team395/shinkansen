package org.usfirst.frc.team395.robot.commands;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFeet extends Command {
	PIDController linearPID = new PIDController(0.03, 0, 0.2, 0, Robot.drivetrainEncoders, Robot.drivetrain);
	double distanceFeet = 0;
	private static double ACCEPTABLE_ERROR = Robot.drivetrainEncoders.convertFeetToNativeUnits(1/6);
	
    public DriveFeet(double distanceFeet) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	setInterruptible(false);
    	
    	this.distanceFeet = distanceFeet;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrainEncoders.resetEncoderValues();
    	
    	double setpoint = Robot.drivetrainEncoders.getAveragedPositions()
    			+ Robot.drivetrainEncoders.convertFeetToNativeUnits(distanceFeet);
    	linearPID.setSetpoint(setpoint);
    	linearPID.setAbsoluteTolerance(ACCEPTABLE_ERROR);
    	linearPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return linearPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    	linearPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Command is not interruptable.
    }
}
