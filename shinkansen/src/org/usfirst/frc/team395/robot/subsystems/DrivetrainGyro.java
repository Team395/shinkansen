package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.Robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainGyro extends Subsystem implements PIDSource {
	PigeonIMU pigeon = new PigeonIMU(Robot.drivetrain.getGyroTalon());
	double lastResponse = -395;
	
	public static class GyroIndex {
		public static int X_INDEX = 0;
		public static int Y_INDEX = 1;
		public static int Z_INDEX = 2;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getAngleZ() {
    	double[] returnArray = new double[3];
    	ErrorCode errorCode = pigeon.getAccumGyro(returnArray);
    	
    	switch(errorCode) {
    	case OK:
    		lastResponse = returnArray[GyroIndex.Z_INDEX];
		default:
			//log and figure out default
    	}
    	
    	return lastResponse;
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
		return getAngleZ();
	}
}

