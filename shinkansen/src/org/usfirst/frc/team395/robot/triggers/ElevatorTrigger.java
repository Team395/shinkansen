package org.usfirst.frc.team395.robot.triggers;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ElevatorTrigger extends Trigger {

	private static final double triggerThreshold = 0.25;
	
    public boolean get() {
        return Math.abs(Robot.oi.getElevatorThrottle()) > triggerThreshold;
    }
}
