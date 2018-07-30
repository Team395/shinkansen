package org.usfirst.frc.team395.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team395.robot.RobotMap;
import org.usfirst.frc.team395.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
	WPI_TalonSRX leftLeader = new WPI_TalonSRX(RobotMap.Drive.leftLeaderTalon);
	WPI_TalonSRX rightLeader = new WPI_TalonSRX(RobotMap.Drive.rightLeaderTalon);
	WPI_TalonSRX leftFollower = new WPI_TalonSRX(RobotMap.Drive.leftFollowerTalon);
	WPI_TalonSRX rightFollower = new WPI_TalonSRX(RobotMap.Drive.rightFollowerTalon);
	
	Map<Integer, WPI_TalonSRX> talonMap = new HashMap<Integer, WPI_TalonSRX>();
	SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftLeader, leftFollower);
	SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightLeader, rightFollower);
	DifferentialDrive differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
	
	public Drivetrain() {
		leftFollower.follow(leftLeader);
		rightFollower.follow(rightLeader);
		
		talonMap.put(leftLeader.getDeviceID(), leftLeader);
		talonMap.put(rightLeader.getDeviceID(), rightLeader);
		talonMap.put(leftFollower.getDeviceID(), leftFollower);
		talonMap.put(rightFollower.getDeviceID(), rightFollower);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public WPI_TalonSRX getLeftEncoderTalon() {
    	return talonMap.get(RobotMap.Sensors.leftEncoderTalon);
    }
    
    public WPI_TalonSRX getRightEncoderTalon() {
    	return talonMap.get(RobotMap.Sensors.rightEncoderTalon);
    }
    
    public WPI_TalonSRX getGyroTalon() {
    	return talonMap.get(RobotMap.Sensors.gyroTalon);
    }

	@Override
	public void pidWrite(double output) {
		tankDrive(output, output);
	}
}

