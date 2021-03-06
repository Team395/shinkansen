/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.triggers;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
* Add your docs here.
*/
public class ThresholdTrigger extends Trigger {
    @Override
    public boolean get() {
        return !Robot.intake.isCubeInIntake() && !Robot.elevator.isElevatorAboveIntakeThreshold();
    }
}
