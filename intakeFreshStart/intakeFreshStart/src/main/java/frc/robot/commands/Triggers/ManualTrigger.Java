package frc.robot.commands.Triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Robot;

/**
 * Add your docs here
 */
public class ManualTrigger extends Trigger {
    @Override
    public boolean get() {
        return Robot.elevator.isElevatorAboveIntakeThreshold() && Robot.intake.getCurrentCommand() == null && !Robot.intake.cubeInIntake();
    }


}