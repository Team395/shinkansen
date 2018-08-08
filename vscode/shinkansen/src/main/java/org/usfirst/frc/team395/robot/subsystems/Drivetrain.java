package org.usfirst.frc.team395.robot.subsystems;

import org.usfirst.frc.team395.robot.Robot;
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
	
	WPI_TalonSRX leftLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.leftLeaderTalon);
	WPI_TalonSRX leftFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.leftFollowerTalon);
	WPI_TalonSRX rightLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.rightLeaderTalon);
	WPI_TalonSRX rightFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.rightFollowerTalon);

	SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftLeader, leftFollower);
	SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightLeader, rightFollower);
	DifferentialDrive differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
	
	public Drivetrain() {
		leftFollower.follow(leftLeader);
		rightFollower.follow(rightLeader);
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
    
	@Override
	public void pidWrite(double output) {
		tankDrive(output, output);
	}
}

