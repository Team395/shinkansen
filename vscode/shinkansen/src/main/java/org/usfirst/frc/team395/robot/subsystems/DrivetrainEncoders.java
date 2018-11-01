package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.Robot;
import org.usfirst.frc.team395.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DrivetrainEncoders implements PIDSource {
	
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
	
    public double getEncoderValue(RobotSide robotSide, ValueType valueType) {
    	switch(valueType) {
	    	case VELOCITY:
	    		switch(robotSide) {
	    			case LEFT:
	    				return Robot.talonMap.getTalonByID(RobotMap.Sensors.leftEncoderTalon).getSelectedSensorVelocity(0);
	    			case RIGHT:
	    			default:
	    				return Robot.talonMap.getTalonByID(RobotMap.Sensors.rightEncoderTalon).getSelectedSensorVelocity(0);
	    		}
	    	case DISPLACEMENT:
			default:
	    		switch(robotSide) {
	    			case LEFT:
						return Robot.talonMap.getTalonByID(RobotMap.Sensors.leftEncoderTalon).getSelectedSensorPosition(0);
	    			case RIGHT:
	    			default:
	    				return Robot.talonMap.getTalonByID(RobotMap.Sensors.rightEncoderTalon).getSelectedSensorPosition(0);
	    		}
    	}
    }
    
    public void resetEncoderValues() {
    	Robot.talonMap.getTalonByID(RobotMap.Sensors.leftEncoderTalon).setSelectedSensorPosition(0, 0, 50);
    	Robot.talonMap.getTalonByID(RobotMap.Sensors.rightEncoderTalon).setSelectedSensorPosition(0, 0, 50);
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
		throw new UnsupportedOperationException();
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getAveragedPositions();
	}
}

