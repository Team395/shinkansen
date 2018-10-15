package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.Robot;
import org.usfirst.frc.team395.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	// Initialize your subsystem here
	WPI_TalonSRX winchController = Robot.talonMap.getTalonByID(RobotMap.Elevator.winchControllerTalon);
	DigitalInput bottomLimit = new DigitalInput(RobotMap.Elevator.bottomLimitSwitch);
	// System constants (initial values)
	double percentOffset = 0.19;
	double minimumOutput = -0.19;
	final static double topPositionInches = 80.0;
	final static double bottomPositionInches = 0.0;
	final static double topPositionUnits = convertInchesToUnits(topPositionInches);
	final static double bottomPositionUnits = convertInchesToUnits(bottomPositionInches);
	final static double UNITS_PER_ROTATION = 4096;
	final static double CASCADE_FACTOR = 1.8; //TODO: Tune
	final static double DRUM_DIAMETER = 2.41; 
	
	public Elevator() {
    	super(0.00025, 0.0, 0.00015);
		
    	//Minimum and maximum percent outputs
		setOutputRange(minimumOutput, 1);
		setInputRange(bottomPositionUnits, topPositionUnits);
		setAbsoluteTolerance(convertInchesToUnits(2));
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
	}
	
	public void initializeSystem() {
		// homeEncoder();
		enable();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getElevatorEncoder() {
    	//TODO: Do we want to accept a ValueType and return velocity as well?
    	return Robot.talonMap.getTalonByID(RobotMap.Sensors.winchEncoderTalon).getSelectedSensorPosition(0);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getElevatorEncoder();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		SmartDashboard.putNumber("PID Output", output);
		SmartDashboard.putNumber("Current Position", convertUnitsToInches(getElevatorEncoder()));
		SmartDashboard.putNumber("Current Setpoint", convertUnitsToInches(getSetpoint()));
		//System.out.println("usePIDOutput: " + output + "\tpercentOffset: " + percentOffset + "\tbottomLimitPressed: "
		//	+ bottomLimitPressed() + "\tgetSetpoint: " + getSetpoint()
		//	+ "\tcurrentPosition: " + getElevatorEncoder());

		output += percentOffset;
		
		if(bottomLimitPressed() && getSetpoint() == 0) {
			output = 0;
			homeEncoder();
		}

		winchController.set(output);
    }
    
    public void setPIDCoefficients(double p, double i, double d, double percentOffset, double minimumOutput) {
    	getPIDController().setPID(p, i, d);
    	this.percentOffset = percentOffset;
    	this.minimumOutput = minimumOutput;
    	setOutputRange(minimumOutput, 1);
    }
    
    public double[] getPIDCoefficients() {
    	double[] coefficients = new double[3];
    	PIDController pidController = getPIDController();
    	coefficients[0] = pidController.getP();
    	coefficients[1] = pidController.getI();
    	coefficients[2] = pidController.getD();
    	return coefficients;
    }
    
    public double getPercentOffset() {
    	return percentOffset;
    }
    
    public double getMinimumOutput() {
    	return minimumOutput;
    }
    
    public boolean bottomLimitPressed() {
    	return !bottomLimit.get();
    }
    
    public void homeEncoder() {
		Robot.talonMap.getTalonByID(RobotMap.Sensors.winchEncoderTalon).setSelectedSensorPosition(0, 0, 50);
    }
    
    public void setElevatorSetpoint(double setpointInches) {
    	double setpointUnits = convertInchesToUnits(setpointInches);
    	setSetpoint(setpointUnits);
	}
	
	public static double convertUnitsToInches(double units) {
		double inches = units * CASCADE_FACTOR * Math.PI * DRUM_DIAMETER / UNITS_PER_ROTATION;

		return inches;
	}

	public static double convertInchesToUnits(double inches) {
		double units = inches * UNITS_PER_ROTATION / CASCADE_FACTOR / Math.PI / DRUM_DIAMETER;

		return units;
	}
}
