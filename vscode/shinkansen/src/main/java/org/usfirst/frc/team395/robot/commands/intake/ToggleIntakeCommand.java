/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.commands.intake;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import org.usfirst.frc.team395.robot.Robot;

public class ToggleIntakeCommand extends ConditionalCommand {
    public ToggleIntakeCommand() {
        super(new AutomaticIntake(), new ManualIntake());
    }
    
    protected boolean condition() {
        return Robot.intake.getCurrentCommand().getName().equals("ManualIntake");
    }
}
