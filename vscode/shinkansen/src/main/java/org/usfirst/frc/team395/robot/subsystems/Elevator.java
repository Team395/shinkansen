package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	// Initialize your subsystem here
	WPI_TalonSRX winchController = new WPI_TalonSRX(RobotMap.Elevator.winchControllerTalon);
	DigitalInput bottomLimit = new DigitalInput(RobotMap.Elevator.bottomLimitSwitch);
	// System constants (initial values)
	double percentOffset = 0.19;
	double minimumOutput = 0.0;
	final double topPositionInches = 100.0;
	final double bottomPositionInches = 0.0;
	final double UNITS_PER_ROTATION = 4096;
	final double CASCADE_FACTOR = 2; //TODO: Tune
	final double DRUM_DIAMETER = 2; //TODO: Test
	
	public Elevator() {
    	super(0, 0, 0);
    	
    	//Minimum and maximum percent outputs
    	setOutputRange(minimumOutput, 1);
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	// TODO: Pick default command
    }
    
    public double getElevatorEncoder() {
    	//TODO: Do we want to accept a ValueType and return velocity as well?
    	return winchController.getSelectedSensorPosition(0);
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
    	if(bottomLimitPressed() && getSetpoint() == 0) {
    		output += 0;
    	}
    	else {
    		output += percentOffset;
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
    	return bottomLimit.get();
    }
    
    public void homeEncoder() {
    	winchController.setSelectedSensorPosition(0, 0, 0);
    }
    
    public void setElevatorSetpoint(double setpointInches) {
    	double setpointTicks = setpointInches * UNITS_PER_ROTATION / (CASCADE_FACTOR * Math.PI * DRUM_DIAMETER);
    	setSetpoint(setpointTicks);
    }
}
