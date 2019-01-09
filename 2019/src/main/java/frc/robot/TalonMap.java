package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonMap {
    Map<Integer, WPI_TalonSRX> talonMap = new HashMap<Integer, WPI_TalonSRX>();

    WPI_TalonSRX leftLeader = new WPI_TalonSRX(RobotMap.Drive.leftLeaderTalon);
	WPI_TalonSRX rightLeader = new WPI_TalonSRX(RobotMap.Drive.rightLeaderTalon);
	WPI_TalonSRX leftFollower = new WPI_TalonSRX(RobotMap.Drive.leftFollowerTalon);
    WPI_TalonSRX rightFollower = new WPI_TalonSRX(RobotMap.Drive.rightFollowerTalon);
    WPI_TalonSRX winchController = new WPI_TalonSRX(RobotMap.Elevator.winchControllerTalon);
    
    public TalonMap() {
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