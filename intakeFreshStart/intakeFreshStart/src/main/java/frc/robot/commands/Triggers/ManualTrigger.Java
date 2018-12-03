package org.usfirst.frc.team395.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Tiggers;
import org.usfirst.frc.team395.robot.Robot;

/**
 * Add your docs here
 */
public class ManualTrigger extends Trigger {
    @Override
    public boolean get() {
        return Robot.elavator.isElavatorAboveIntakeThreshold() && Robot.intake.getCurrentCommand() == null && !Robot.intake.cubeInIntake();
    }


}