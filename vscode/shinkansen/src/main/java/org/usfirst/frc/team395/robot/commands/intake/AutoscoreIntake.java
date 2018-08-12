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

public class AutoscoreIntake extends Command {

    private static final double SCORE_TIME = 0.5;

    private Timer timer = new Timer();
    private double throttle;
    
    public AutoscoreIntake() {
        this(0.4);
    }

    public AutoscoreIntake(double throttle) {
        requires(Robot.intake);
        this.throttle = throttle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        timer.reset();
        timer.start();

        Robot.intake.setIntakeSpeed(throttle);
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return timer.get() > SCORE_TIME;
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intake.setIntakeSpeed(0);
        Robot.intake.openIntake();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
