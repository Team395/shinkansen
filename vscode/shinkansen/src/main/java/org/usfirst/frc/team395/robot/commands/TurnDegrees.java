// TODO: Uncomment

// package org.usfirst.frc.team395.robot.commands;

// import org.usfirst.frc.team395.robot.Robot;

// import edu.wpi.first.wpilibj.PIDController;
// import edu.wpi.first.wpilibj.command.Command;

// /**
//  *
//  */
// public class TurnDegrees extends Command {
	
// 	PIDController turnPID = new PIDController(0, 0, 0, 0, Robot.drivetrainGyro, Robot.drivetrain);
// 	double turnDegrees = 0.0;
// 	double ACCEPTABLE_ERROR_DEGREES = 1.5;

//     public TurnDegrees(double turnDegrees) {
//         // Use requires() here to declare subsystem dependencies
//         // eg. requires(chassis);
//        	requires(Robot.drivetrain);
//     	setInterruptible(false);
 
//     	this.turnDegrees = turnDegrees;
//     }

//     // Called just before this Command runs the first time
//     protected void initialize() {
//     	turnPID.setSetpoint(Robot.drivetrainGyro.getAngleZ() + turnDegrees);
//     	turnPID.setAbsoluteTolerance(ACCEPTABLE_ERROR_DEGREES);
//     	turnPID.enable();
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         return turnPID.onTarget();
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//     	Robot.drivetrain.tankDrive(0, 0);
//     	turnPID.disable();
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems is scheduled to run
//     protected void interrupted() {
//     }
// }
