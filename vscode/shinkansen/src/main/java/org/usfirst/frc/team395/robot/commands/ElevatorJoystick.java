package org.usfirst.frc.team395.robot.commands;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team395.robot.subsystems.Elevator;
/**
 *
 */

public class ElevatorJoystick extends Command {
	 
    public ElevatorJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.elevator.setElevatorSetpoint(Elevator.convertUnitsToInches(Robot.elevator.getElevatorEncoder()));
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Math.abs(Elevator.convertUnitsToInches(Robot.elevator.getElevatorEncoder()) - Elevator.convertUnitsToInches(Robot.elevator.getSetpoint())) < 3){
            Robot.elevator.setElevatorSetpoint(Elevator.convertUnitsToInches(Robot.elevator.getSetpoint())
            + Robot.oi.getElevatorThrottle() * 0.3);    
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.elevatorTrigger.get();
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
