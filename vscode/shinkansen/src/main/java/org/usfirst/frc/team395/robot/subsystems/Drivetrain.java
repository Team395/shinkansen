package org.usfirst.frc.team395.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsytem;
import com.ctre.phoneix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team395.robot.Robot;
import org.usfirst.frc.team395.robot.Robotmap;

public class Drivetrain extends Subsystem {

    private WPI_TalonSRX leftLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.leftLeaderTalon);
    private WPI_TalonSRX leftFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.leftFollowerTalon);
    private WPI_TalonSRX rightLeader = Robot.talonMap.getTalonByID(RobotMap.Drive.rightLeaderTalon);
    private WPI_TalonSRX rightFollower = Robot.talonMap.getTalonByID(RobotMap.Drive.rightFollowerTalon);

    private SpeedControllerGroup leftSpeedController = new SpeedControllerGroup (leftLeader, leftFollower);
    private SpeedControllerGroup rightSpeedController = new SpeedControllerGroup(rightLeader, rightFollower);

    public void initDefaultCommmand() {

        setDefaultCommand(new TankDrive());

    }

    public void tankDrive(double left, double right) {
        
        leftSpeedController.set(left);
        rightSpeedController.set(right);
        
    }
}

