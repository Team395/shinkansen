/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DrivetrainGyro implements PIDSource {
	PigeonIMU pigeon = new PigeonIMU(Robot.talonMap.getTalonByID(RobotMap.Sensors.gyroTalon));
	double lastResponse = -395;
	
	public static class GyroIndex {
		public static int X_INDEX = 0;
		public static int Y_INDEX = 1;
		public static int Z_INDEX = 2;
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
		throw new UnsupportedOperationException();
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getAngleZ();
    }
}