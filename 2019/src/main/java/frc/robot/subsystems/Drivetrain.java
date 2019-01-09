/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem implements PIDOutput {
	
	WPI_TalonSRX leftLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.leftLeaderTalon);
	WPI_TalonSRX leftFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.leftFollowerTalon);
	WPI_TalonSRX rightLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.rightLeaderTalon);
	WPI_TalonSRX rightFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.rightFollowerTalon);

	SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftLeader, leftFollower);
	SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightLeader, rightFollower);
	DifferentialDrive differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
	
	public Drivetrain() {
		//rightSpeedControllers.setInverted(true);
		leftFollower.follow(leftLeader);
		rightFollower.follow(rightLeader);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new TankDrive());
//TODO: Uncomment
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
	@Override
	public void pidWrite(double output) {
		tankDrive(output, output);
	}
}

