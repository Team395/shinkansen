/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems; 

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ManualIntake;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.Intake.AutomaticIntake;
import frc.robot.commands.Triggers.AutomaticTrigger;
import frc.robot.commands.Triggers.ManualTrigger;
import frc.robot.commands.Triggers.ThresholdTrigger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  XboxController xboxController = new XboxController(2);
  Button backButton = new JoystickButton(xboxController, 7); // Check NUmebr on THe THING!  
  
  
  AutomaticTrigger automaticTrigger = new AutomaticTrigger();
  ManualTrigger manualTrigger = new ManualTrigger();
  ThresholdTrigger thresholdTrigger = new ThresholdTrigger();
  // Joystick leftJoy = new Joystick(1);
  // Button button1 = new JoystickButton(leftJoy, 1), // ask Henry if this is ok? also named button 1, 4 Bonji to name
  //        button2 = new JoystickButton(leftJoy, 2);
 //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  
    
  
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public void setUpTriggers() {
    manualTrigger.whenActive(new ManualIntake());
    automaticTrigger.whenActive(new AutomaticIntake());

  }

  public OI() { // something defo wrong with this line 
    backButton.whenPressed(new ToggleIntake());

  }

public boolean getClawOpen(){
    return xboxController.getTriggerAxis(Hand.kRight) > .5;

  }

 
  public boolean getClawClosed(){
    return xboxController.getTriggerAxis(Hand.kLeft) > .5;
  }

  public double getClawSpeed(){
    return xboxController.getY(Hand.kRight);
  }
}

