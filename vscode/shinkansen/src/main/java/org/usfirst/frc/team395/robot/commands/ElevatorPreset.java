package org.usfirst.frc.team395.robot.commands;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorPreset extends Command {
	
	private double height;
	
	
	public enum PresetHeight {	
		HIGH_SCALE(77), 
		NORMAL_SCALE(70),
		LOW_SCALE(61),
		SWITCH(30),
		BOTTOM(0);
		//TODO: retune 
		
		private double height;
		
		PresetHeight(double height) {
			this.height = height;
		}
		
		public double getHeight() {	
			return height;
		}
	}
	
	
    public ElevatorPreset(PresetHeight presetHeight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
		this.height = presetHeight.getHeight();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setElevatorSetpoint(height);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
