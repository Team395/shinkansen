package frc.robot.commands.Triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Robot;

/**
 * Add your docs herer.
 */
public class AutomaticTrigger extends Trigger { 
    @Override
    public boolean get() {
     return Robot.intake.getCurrentCommand() == null && !Robot.intake.cubeInIntake();// && !Robot.elevator.isElevatorAboveIntakeThreshold();
    }

}