package org.usfirst.frc.team395.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team395.robot.Robot;
import org.usfirst.frc.team395.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	// Initialize your subsystem here
	WPI_TalonSRX winchController = Robot.talonMap.getTalonByID(RobotMap.Elevator.winchControllerTalon);
	DigitalInput bottomLimit = new DigitalInput(RobotMap.Elevator.bottomLimitSwitch);
	// System constants (initial values)
	double percentOffset = 0.19;
	final static double topPositionInches = 80;
	final static double bottomPositionInches = 0.0;
	final static double topPositionUnits = convertInchesToUnits(topPositionInches);
	final static double bottomPositionUnits = convertInchesToUnits(bottomPositionInches);
	final static double UNITS_PER_ROTATION = 4096;
	final static double CASCADE_FACTOR = 1.8;
	final static double DRUM_DIAMETER = 2.41; 
	final static double INTAKE_THRESHOLD = 15;
	
	public Elevator() {
		winchController.config_kP(0, 0.5, 10);
		winchController.config_kI(0, 0, 10);
		winchController.config_kD(0, 0, 10); //TODO: tune further
		winchController.configPeakOutputReverse(.3, 10);
		winchController.configPeakOutputForward(1, 10);
		winchController.configAllowableClosedloopError(0, 0, 10);
	}
	
	public void initializeSystem() {
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getElevatorEncoder() {
    	return winchController.getSelectedSensorPosition(0);
    }

    public void setPIDCoefficients(double p, double i, double d, double percentOffset) {
		winchController.config_kP(0, p, 10);
		winchController.config_kI(0, i, 10);
		winchController.config_kD(0, d, 10);
    	this.percentOffset = percentOffset;
    }
        
    public boolean bottomLimitPressed() {
    	return !bottomLimit.get();
    }
    
    public void homeEncoder() {
		winchController.setSelectedSensorPosition(0, 0, 10);
    }
    
    public void setElevatorSetpoint(double setpointInches) {
    	double setpointUnits = convertInchesToUnits(setpointInches);
		winchController.set(ControlMode.Position, setpointUnits, DemandType.ArbitraryFeedForward, percentOffset);
	}
	
	public static double convertUnitsToInches(double units) {
		double inches = units * CASCADE_FACTOR * Math.PI * DRUM_DIAMETER / UNITS_PER_ROTATION;

		return inches;
	}

	public boolean isElevatorAboveIntakeThreshold() { 
        return convertUnitsToInches(getElevatorEncoder()) > INTAKE_THRESHOLD;
	}
	
	public static double convertInchesToUnits(double inches) {
		double units = inches * UNITS_PER_ROTATION / CASCADE_FACTOR / Math.PI / DRUM_DIAMETER;

		return units;
	}

	public double getSetpoint() {
		return winchController.getClosedLoopTarget(0);
	}

	public boolean onTarget() {
		return Math.abs(winchController.getClosedLoopError(0) - winchController.getClosedLoopTarget(0)) <  (int) convertInchesToUnits(2);
	}
}
