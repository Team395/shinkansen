/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ElevatorPreset.PresetHeight;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ElevatorJoystick;
import frc.robot.commands.ElevatorPreset;
import frc.robot.commands.intake.AutomaticIntake;
import frc.robot.commands.intake.AutoscoreIntake;
import frc.robot.commands.intake.ManualIntake;
import frc.robot.commands.intake.ToggleIntakeCommand;
import frc.robot.triggers.AutomaticTrigger;
import frc.robot.triggers.ElevatorTrigger;
import frc.robot.triggers.ManualTrigger;
import frc.robot.triggers.ThresholdTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static int INVERT = -1;

	private Joystick leftStick = new Joystick(RobotMap.leftStick);
	private Joystick rightStick = new Joystick(RobotMap.rightStick);
	private XboxController xboxController = new XboxController(RobotMap.xboxController);
	
	
	
	public ElevatorTrigger elevatorTrigger;
	Button highScaleHeight = new JoystickButton(xboxController, 4);
	Button normalScaleHeight = new JoystickButton(xboxController, 2);
	Button lowScaleHeight = new JoystickButton(xboxController, 1);
	Button switchHeight = new JoystickButton(xboxController, 3);
	Button bottomHeight = new JoystickButton(xboxController, 9);
	
	Button autoscoreButton = new JoystickButton(xboxController, 10);
	Button toggleManual = new JoystickButton(xboxController, 7);
	ManualTrigger manualTrigger = new ManualTrigger();
	//RetainTrigger retainTrigger = new RetainTrigger();
	AutomaticTrigger automaticTrigger = new AutomaticTrigger();
	ThresholdTrigger thresholdTrigger = new ThresholdTrigger();
	
	public void setUpTriggers() {
		//elevatorTrigger = new ElevatorTrigger();
		//elevatorTrigger.whenActive(new ElevatorJoystick());
		//highScaleHeight.whenPressed(new ElevatorPreset(PresetHeight.HIGH_SCALE));
		//normalScaleHeight.whenPressed(new ElevatorPreset(PresetHeight.NORMAL_SCALE));
		//lowScaleHeight.whenPressed(new ElevatorPreset(PresetHeight.LOW_SCALE));
		//switchHeight.whenPressed(new ElevatorPreset(PresetHeight.SWITCH));
        //bottomHeight.whenPressed(new ElevatorPreset(PresetHeight.BOTTOM));
        
        //Intake Triggers
        //manualTrigger.whenActive(new ManualIntake());
        //autoscoreButton.whenPressed(new AutoscoreIntake());
        //retainTrigger.whenActive(new RetainIntake());
        //automaticTrigger.whenActive(new AutomaticIntake());
        //thresholdTrigger.whenActive(new AutomaticIntake());
		//toggleManual.whenPressed(new ToggleIntakeCommand());
    }
	
	public double getLeftY() {
		return INVERT * leftStick.getY();
	}
	
	public double getRightY() {
		return INVERT * rightStick.getY();
	}
	
	public double getElevatorThrottle() {
		return INVERT * xboxController.getY(Hand.kLeft);	
	}

    public boolean getCloseIntake() {
        return xboxController.getTriggerAxis(Hand.kLeft) > 0.5;
    }

    public boolean getOpenIntake() {
        return xboxController.getTriggerAxis(Hand.kRight) > 0.5;
    }

    public double getIntakeThrottle() {
        return INVERT * xboxController.getY(Hand.kRight);
    }

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
	
	
}
