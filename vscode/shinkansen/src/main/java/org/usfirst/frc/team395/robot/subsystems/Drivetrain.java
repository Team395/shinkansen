package org.usfirst.frc.team395.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team395.robot.RobotMap;
import org.usfirst.frc.team395.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team395.robot.Robot;

public class Drivetrain extends Subsystem implements PIDOutput{

    private WPI_TalonSRX leftLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.leftLeaderTalon);
    private WPI_TalonSRX leftFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.leftFollowerTalon);
    private WPI_TalonSRX rightLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.rightLeaderTalon);
    private WPI_TalonSRX rightFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.rightFollowerTalon);

    private SpeedControllerGroup leftSpeedController = new SpeedControllerGroup (leftLeader, leftFollower);
    private SpeedControllerGroup rightSpeedController = new SpeedControllerGroup(rightLeader, rightFollower);

    public void initDefaultCommand() {

        setDefaultCommand(new TankDrive());

    }

    public void tankDrive(double left, double right) {
        
        leftSpeedController.set(left);
        rightSpeedController.set(right*-1);
        
    }

    public void pidWrite(double speed) {

    }
}

