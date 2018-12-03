/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team395.robot.Robot;

/**
* Add your docs here.
*/
public class ManualTrigger extends Trigger {
    @Override
    public boolean get() {
        return Robot.intake.getCurrentCommand() == null &&
               (Robot.elevator.isElevatorAboveIntakeThreshold() && !Robot.intake.isCubeInIntake()) ||
               (!Robot.intake.isIntakeOpen() && Robot.intake.isCubeInIntake());
    }
}
