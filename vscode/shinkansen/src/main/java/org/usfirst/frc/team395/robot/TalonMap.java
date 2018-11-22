package org.usfirst.frc.team395.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/** This class holds all the talon objects for the robot so they can be constructed
 * once and then accessed for both setting motor speeds and getting sensor positions
 * in different classes without constructing multiple instances or passing around 
 * talon objects internally.
 */

public class TalonMap {
    Map<Integer, WPI_TalonSRX> talonMap = new HashMap<Integer, WPI_TalonSRX>();

    WPI_TalonSRX leftLeader = new WPI_TalonSRX(RobotMap.Drive.leftLeaderTalon);
	WPI_TalonSRX rightLeader = new WPI_TalonSRX(RobotMap.Drive.rightLeaderTalon);
	WPI_TalonSRX leftFollower = new WPI_TalonSRX(RobotMap.Drive.leftFollowerTalon);
    WPI_TalonSRX rightFollower = new WPI_TalonSRX(RobotMap.Drive.rightFollowerTalon);
    WPI_TalonSRX winchController = new WPI_TalonSRX(RobotMap.Elevator.winchControllerTalon);
    
    public TalonMap() {
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

        //Config leftLeader
        leftLeader.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 0, 10);
        leftLeader.configRemoteFeedbackFilter(RobotMap.Sensors.gyroTalon, RemoteSensorSource.GadgeteerPigeon_Yaw, 1, 10);
    
        leftLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        leftLeader.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 1, 10);

        //Config leftFollower
        leftFollower.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 0, 10);
        leftFollower.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 1, 10);
    
        leftFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        leftFollower.configSelectedFeedbackSensor(FeedbackDevice.None, 1, 10);

        //Config rightLeader
        rightLeader.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 0, 10);
        rightLeader.configRemoteFeedbackFilter(RobotMap.Sensors.gyroTalon, RemoteSensorSource.GadgeteerPigeon_Yaw, 1, 10);
    
        rightLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        rightLeader.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 1, 10);

        //Config rightFollower
        rightFollower.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 0, 10);
        rightFollower.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 1, 10);
    
        rightFollower.configSelectedFeedbackSensor(FeedbackDevice.None, 0, 10);
        rightFollower.configSelectedFeedbackSensor(FeedbackDevice.None, 1, 10);

        //Config winchController
        winchController.configRemoteFeedbackFilter(RobotMap.Sensors.winchEncoderTalon, RemoteSensorSource.TalonSRX_SelectedSensor, 0, 10);
        winchController.configRemoteFeedbackFilter(0, RemoteSensorSource.Off, 1, 10);
    
        winchController.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, 0, 10);
        winchController.configSelectedFeedbackSensor(FeedbackDevice.None, 1, 10);
        
        talonMap.put(leftLeader.getDeviceID(), leftLeader);
        talonMap.put(rightLeader.getDeviceID(), rightLeader);
        talonMap.put(leftFollower.getDeviceID(), leftFollower);
        talonMap.put(rightFollower.getDeviceID(), rightFollower);
        talonMap.put(winchController.getDeviceID(), winchController);
    }

    public WPI_TalonSRX getTalonByID(Integer talonID) {
        return talonMap.get(talonID);
    }
}