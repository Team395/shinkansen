/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team395.robot.Robot;

public class ToggleIntakeCommand extends Command {

	// Called just before this Command runs the first time
	public void initialize() {
		System.out.println("Initialized");
		if(Robot.intake.getCurrentCommandName().equals("ManualIntake")) {
			System.out.println("Toggled Auto");
			Scheduler.getInstance().add(new AutomaticIntake());
		} else {
			System.out.println("Toggled Manual");
			Scheduler.getInstance().add(new ManualIntake());
		}
	}

	public void execute() {

	}

	public boolean isFinished() {
		return true;
	}

	public void end() {

	}

	public void interrupted() {

	}

}
