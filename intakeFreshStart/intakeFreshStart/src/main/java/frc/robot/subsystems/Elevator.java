/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends PIDSubsystem {
    WPI_TalonSRX winchController = new WPI_TalonSRX(RobotMap.Elevator.winchControllerTalon);
	DigitalInput bottomLimit = new DigitalInput(RobotMap.Elevator.bottomLimitSwitch);   
 // Put methods for controlling this subsystem
// here. Call these from Commands.

public Elevator(){
    super(0, 0, 0);
            /* 
            Sensor position 0:
            leftLeader - leftEncoder
            leftFollower - winchEncoder
            rightLeader - rightEncoder
            rightFollower - None
            winchController - winchEncoder
            Sensor position 1:
            leftLeader - pigeonYaw
            leftFollower - None
            rightLeader - pigeonYaw
            rightFollower - None
            winchController - None
        */
        winchController.configRemoteFeedbackFilter(RobotMap.Elevator.winchEncoderTalon, RemoteSensorSource.TalonSRX_SelectedSensor, 0, 10);
        winchController.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 1, 10);
    
        winchController.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, 0, 10);
        winchController.configSelectedFeedbackSensor(FeedbackDevice.None, 1, 10);

    winchController.getSelectedSensorPosition(0);
}

// public void setPIDNumeros(double p, double i, double d) {
//     getPIDController().setPID(p, i, d);
// }
  
    // private Object getPIDController() {
    //     return null;
    // }

    @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

    @Override
    protected double returnPIDInput() {
        return 0;
    }

    @Override
    protected void usePIDOutput(double output) {

    }
}
