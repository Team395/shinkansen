/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.commands.intake;

import org.usfirst.frc.team395.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RetainIntake extends Command {
    private static final double RETAIN_PERIOD_LENGTH = 8;
    private static final double RETAIN_DUTY_CYCLE = 0;
    private Timer timer = new Timer();

    public RetainIntake() {
        super("RetainIntake");
        requires(Robot.intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //Reset the timer if it is longer than one period
        if(timer.get() > RETAIN_PERIOD_LENGTH) {
            timer.reset();
        }

        //If the timer is in the first RETAIN_DUTY_CYCLE fraction of one period, retain the cube
        if(timer.get() < RETAIN_PERIOD_LENGTH * RETAIN_DUTY_CYCLE) {
            Robot.intake.setIntakeSpeed(-1);
        } else {
            Robot.intake.setIntakeSpeed(0);
        }

        //If a manual command is made, honor it
        if(Robot.oi.getIntakeThrottle() != 0) {
            Robot.intake.setIntakeSpeed(Robot.oi.getIntakeThrottle());
        }
        
        if(Robot.oi.getOpenIntake()) {
            Robot.intake.openIntake();
        }

        if(Robot.oi.getCloseIntake()) {
            Robot.intake.closeIntake();
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !Robot.intake.isCubeInIntake();
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
        System.out.println("Retain Ended");
        Robot.intake.setIntakeSpeed(0);
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        System.out.println("Retain Interrupted");
        end();
    }
}
