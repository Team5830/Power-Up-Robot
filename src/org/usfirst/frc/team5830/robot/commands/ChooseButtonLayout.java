package org.usfirst.frc.team5830.robot.commands;

import org.usfirst.frc.team5830.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 * @author Hunter Pugh
 * DEPRECATED! To avoid nullPointerExceptions, this code was moved directly into teleopInit. Still have no idea why that worked.
 * Gets the status of the SmartDashboard chooser for Joystick Input, 
 * then creates and maps joystick buttons. Axes definition in Robot.teleopPeriodic
 */

public class ChooseButtonLayout extends InstantCommand {
	

    public ChooseButtonLayout() {
    }
    
    protected void execute() {
    	//There is no isFinished defined because this is an InstantCommand. 
    	//An InstantCommand is just shorthand for returning true in isFinished, meaning execute will only run once.
    	switch (Robot.controlType.getSelected()) {
    		case 0: //General Flightsticks (Default)
    			Robot.leftJoy = new Joystick(0);
    			Robot.rightJoy = new Joystick(1);
    			Robot.button1 = new JoystickButton(Robot.leftJoy, 1);             //Trigger
    			Robot.button2 = new JoystickButton(Robot.rightJoy, 1);            //Trigger
    			Robot.buttonCubeToScale = new JoystickButton(Robot.rightJoy,6);   //(Top) Upper Right
    			Robot.buttonCubeToSwitch = new JoystickButton(Robot.rightJoy,5);  //(Top) Upper Left
    			Robot.buttonCubeToGround1 = new JoystickButton(Robot.rightJoy,3); //(Top) Lower Left
    			Robot.buttonCubeToGround2 = new JoystickButton(Robot.rightJoy,4); //(Top) Lower Right
    			Robot.buttonWinchRelease = new JoystickButton(Robot.rightJoy,11);
    			break;
    		case 1: //General Xbox
    			Robot.button1 = new JoystickButton(Robot.xbox, 6);            //LB
    			Robot.button2 = new JoystickButton(Robot.xbox, 5);            //RB
    			Robot.buttonCubeToScale = new JoystickButton(Robot.xbox,4);   //Y
    			Robot.buttonCubeToSwitch = new JoystickButton(Robot.xbox,2);  //B
    			Robot.buttonCubeToGround1 = new JoystickButton(Robot.xbox,1); //A
    			Robot.buttonWinchRelease = new JoystickButton(Robot.xbox,3);  //X
    			break;
    		case 2: //Daniel
    			Robot.xbox = new Joystick(2);
    			Robot.button1 = new JoystickButton(Robot.xbox, 6);            //LB
    			Robot.button2 = new JoystickButton(Robot.xbox, 5);            //RB
    			Robot.buttonCubeToScale = new JoystickButton(Robot.xbox,4);   //Y
    			Robot.buttonCubeToSwitch = new JoystickButton(Robot.xbox,2);  //B
    			Robot.buttonCubeToGround1 = new JoystickButton(Robot.xbox,1); //A
    			Robot.buttonWinchRelease = new JoystickButton(Robot.xbox,3);  //X
    			break;
    		case 3: //Hannah
    			Robot.leftJoy = new Joystick(0);
    			Robot.rightJoy = new Joystick(1);
    			Robot.button1 = new JoystickButton(Robot.leftJoy, 1);             //Trigger
    			Robot.button2 = new JoystickButton(Robot.rightJoy, 1);            //Trigger
    			Robot.buttonCubeToScale = new JoystickButton(Robot.rightJoy,6);   //(Top) Upper Right
    			Robot.buttonCubeToSwitch = new JoystickButton(Robot.rightJoy,5);  //(Top) Upper Left
    			Robot.buttonCubeToGround1 = new JoystickButton(Robot.rightJoy,3); //(Top) Lower Left
    			Robot.buttonCubeToGround2 = new JoystickButton(Robot.rightJoy,4); //(Top) Lower Right
    			Robot.buttonWinchRelease = new JoystickButton(Robot.rightJoy,11);
    			break;
    		case 4: //Hunter
    			Robot.leftJoy = new Joystick(0);
    			Robot.rightJoy = new Joystick(1);
    			Robot.button1 = new JoystickButton(Robot.leftJoy, 1);             //Trigger
    			Robot.button2 = new JoystickButton(Robot.rightJoy, 1);            //Trigger
    			Robot.buttonCubeToScale = new JoystickButton(Robot.rightJoy,6);   //(Top) Upper Right
    			Robot.buttonCubeToSwitch = new JoystickButton(Robot.rightJoy,5);  //(Top) Upper Left
    			Robot.buttonCubeToGround1 = new JoystickButton(Robot.rightJoy,3); //(Top) Lower Left
    			Robot.buttonCubeToGround2 = new JoystickButton(Robot.rightJoy,4); //(Top) Lower Right
    			Robot.buttonWinchRelease = new JoystickButton(Robot.rightJoy,11);
    			break;
    	}
    }
}
