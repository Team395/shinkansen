/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team395.robot.commands.intake;

import org.usfirst.frc.team395.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualIntake extends Command {
    public ManualIntake() {
        super("ManualIntake");
        requires(Robot.intake);

        setInterruptible(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(Robot.oi.getCloseIntake()) {
            Robot.intake.closeIntake();
        }
        if(Robot.oi.getOpenIntake()) {
            Robot.intake.openIntake();
        }
        Robot.intake.setIntakeSpeed(Robot.oi.getIntakeThrottle());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
