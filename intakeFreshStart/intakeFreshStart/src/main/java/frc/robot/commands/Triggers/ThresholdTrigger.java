package frc.robot.commands.Triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
//import frc.robot.Robot;

/**
 * Add your docs here
 */
public class ThresholdTrigger extends Trigger {
    @Override
    public boolean get() {
        return false;//!Robot.intake.cubeInIntake() && !Robot.elevator.isElevatorAboveIntakeThreshold();

    }


}