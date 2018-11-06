package org.usfirst.frc.team395.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team395.robot.Robot;

public class ToggleDriveTrainMode extends Command {

    @Override
    public void initialize() {
        if (Robot.drivetrain.getCurrentCommandName().equals("ArcadeDrive")) {
            Scheduler.getInstance().add(new TankDrive());
        }
        else {
            Scheduler.getInstance().add(new ArcadeDrive());
        }
    }
    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end() {

    }

    @Override
    public void interrupted() {

    } 
}


