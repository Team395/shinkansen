package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainEncoders extends Subsystem implements PIDSource {
	
	public enum RobotSide {
		LEFT,
		RIGHT
	}
	
	public enum ValueType {
		DISPLACEMENT,
		VELOCITY
	}
	
	private static int INCHES_PER_FOOT = 12;
	private static int WHEEL_DIAMETER_INCHES = 4;
	private static int UNITS_PER_ROTATION = 4096;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getEncoderValue(RobotSide robotSide, ValueType valueType) {
    	switch(valueType) {
	    	case VELOCITY:
	    		switch(robotSide) {
	    			case LEFT:
	    				return Robot.drivetrain.getLeftEncoderTalon().getSelectedSensorVelocity(0);
	    			case RIGHT:
	    			default:
	    				return Robot.drivetrain.getRightEncoderTalon().getSelectedSensorVelocity(0);
	    		}
	    	case DISPLACEMENT:
			default:
	    		switch(robotSide) {
	    			case LEFT:
						return Robot.drivetrain.getLeftEncoderTalon().getSelectedSensorPosition(0);
	    			case RIGHT:
	    			default:
	    				return Robot.drivetrain.getRightEncoderTalon().getSelectedSensorPosition(0);
	    		}
    	}
    }
    
    public void resetEncoderValues() {
    	Robot.drivetrain.getLeftEncoderTalon().setSelectedSensorPosition(0, 0, 50);
    	Robot.drivetrain.getRightEncoderTalon().setSelectedSensorPosition(0, 0, 50);
    }
    
    public double getAveragedPositions() {
    	return (getEncoderValue(RobotSide.LEFT, ValueType.DISPLACEMENT)
				+ getEncoderValue(RobotSide.RIGHT, ValueType.DISPLACEMENT)) / 2;
    }
    
    public double convertFeetToNativeUnits(double feet) {
    	return feet * INCHES_PER_FOOT * UNITS_PER_ROTATION / Math.PI / WHEEL_DIAMETER_INCHES;
    }

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getAveragedPositions();
	}
}

