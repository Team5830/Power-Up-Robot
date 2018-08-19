/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import org.usfirst.frc.team5830.robot.commands.AutoLogicMain;
import org.usfirst.frc.team5830.robot.commands.CubeToGround;
import org.usfirst.frc.team5830.robot.commands.CubeToScale;
import org.usfirst.frc.team5830.robot.commands.CubeToSwitch;
import org.usfirst.frc.team5830.robot.commands.DriveBalance;
import org.usfirst.frc.team5830.robot.commands.DriveRotationSetpoint;
import org.usfirst.frc.team5830.robot.commands.SuckCube;
import org.usfirst.frc.team5830.robot.commands.TeleopSpitCube;
import org.usfirst.frc.team5830.robot.commands.WinchRelease;
import org.usfirst.frc.team5830.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.LIDARSubsystem;
import org.usfirst.frc.team5830.robot.subsystems.PIDElevator;
import org.usfirst.frc.team5830.robot.subsystems.PIDLIDARDistance;
import org.usfirst.frc.team5830.robot.subsystems.PIDRotationCorrection;
import org.usfirst.frc.team5830.robot.subsystems.PIDWheelDistance;
import org.usfirst.frc.team5830.robot.subsystems.PIDWinch;
import org.usfirst.frc.team5830.robot.subsystems.PowerCube;
import org.usfirst.frc.team5830.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team5830.robot.subsystems.WheelDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * VARIABLES DEFINED HERE under "User-Defined Variables"
 */

public class Robot extends TimedRobot{ 
	
	/**
	 * User-Defined Variables
	 */

	//Distance from wall to Scale
	public static final double distanceWallToScale = 305; //Inches
	//Distance from wall to Switch
	public static final double distanceWallToSwitch = 130; //Inches
	//Distance from wall to Auto Line
	public static final double distanceWallToAuto = 125; //Inches
	//Distance from wall to Center Position Auto Line
	public static final double distanceWallToCAuto = 115; //Inches
	//Distance from 10 inches from wall to 10 inches from switch, traveling at 45deg angle. Remember, the robot needs room to rotate after getting there
	public static final double distanceCWallToSwitch = 130;//Inches //TODO Calibrate value - this value is arbitrary
	//Balance protection elevator height theshold
	public static final double balanceProtectionElevatorHeight = 1000; //For comparison, ground height is ~300, full raise is ~6000
	//Xbox 360 stick deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	private static final double xbox360Deadzone = 0.1;
	//Distance from LIDAR cube has to be to switch intake from sucking to spitting
	public static final double cubeDistance = 6; //Inches
	//Maximum elevator speed up
	public static final double maxElevatorSpeedUp = 1; //Between 0 and 1
	//Maximum elevator speed up
	public static final double maxElevatorSpeedDown = -0.75; //Between -1 and 0
	
	/**
	 * System-Defined Variables
	 */
	
	//PID
	public static double pidOutputRobot;
	public static double pidOutputAngle;
	public static double pidOutputWheel;
	
	//Joystick inputs
	public static double driveY;
	public static double driveX;
	public static double rotX;
	public static int povPosition;
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static Joystick xbox;
	
	public static Button button1;
	public static Button button2;
	public static Button buttonCubeToScale;
	public static Button buttonCubeToSwitch;
	public static Button buttonCubeToGround1;
	public static Button buttonCubeToGround2;
	public static Button buttonWinchRelease;
	
	//Misc
	public static Command autonomousCommand;
	public static SendableChooser<String> autoPosition = new SendableChooser<>();
	public static SendableChooser<String> autoChooser = new SendableChooser<>();
	public static SendableChooser<Boolean> driveType = new SendableChooser<>();
	public static SendableChooser<Integer> controlType = new SendableChooser<>();
	public static SendableChooser<Command> autoTest = new SendableChooser<>();
	public static boolean isFieldOriented = false;
	public static OI m_oi;
	
	//Swerve Drive
	public static WheelDrive backRight = new WheelDrive (0, 1, 0);
	public static WheelDrive backLeft = new WheelDrive (2, 3, 1);
	public static WheelDrive frontRight = new WheelDrive (4, 5, 2);
	public static WheelDrive frontLeft = new WheelDrive (6, 7, 3);
	public static SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft);
	
	//Vision Processing
	public NetworkTableEntry visionX;
	public NetworkTableEntry visionY;
	private NetworkTableInstance inst = NetworkTableInstance.getDefault();
	private NetworkTable table = inst.getTable("grip");//TODO Change to match GRIP output, as well as in teleopPeriodic
	
	/**
	 * Subsystems
	 */
	
	//Regular
	//public static final Elevator ELEVATOR = new Elevator();
	public static final PowerCube POWERCUBE = new PowerCube();
	
	//LIDAR
	public static final LIDARSubsystem lidarSubsystem = new LIDARSubsystem();
	
	//PID Loops
	public static final PIDLIDARDistance auto_LIDAR_Distance_Swerve = new PIDLIDARDistance();
	public static final PIDRotationCorrection auto_GYRO_Correction_Swerve = new PIDRotationCorrection();
	public static final PIDElevator ELEVATORPID = new PIDElevator();
	public static final PIDWinch WINCHPID = new PIDWinch();
	public static final PIDWheelDistance WHEELDISTANCEPID = new PIDWheelDistance();
	public static final GyroSubsystem GYROSUBSYSTEM = new GyroSubsystem();
	
	/**
	 * Commands
	 */
	//private static Command chooseButtonLayout = new ChooseButtonLayout();
	
	private static Command commandSuckCube = new SuckCube();
	private static Command commandSpitCube = new TeleopSpitCube();
	private static Command driveBalance = new DriveBalance();
	private static Command rotateTo0 = new DriveRotationSetpoint(0);
	private static Command rotateTo45 = new DriveRotationSetpoint(45);
	private static Command rotateTo90 = new DriveRotationSetpoint(90);
	private static Command rotateTo180 = new DriveRotationSetpoint(180);
	private static Command rotateToNeg90 = new DriveRotationSetpoint(-90);
	private static Command rotateToNeg45 = new DriveRotationSetpoint(-45);
	private static Command autoLogicMain = new AutoLogicMain();
	
	//This block was used for testing before the drive CIMcoder was installed. Same thing as DriveMotionProfiling except distance was disabled.
	/*
	private static Command driveBalance = new DriveBalance();
	private static Command rotateTo0 = new TESTDriveRotation(0);
	private static Command rotateTo45 = new TESTDriveRotation(45);
	private static Command rotateTo90 = new TESTDriveRotation(90);
	private static Command rotateTo180 = new TESTDriveRotation(180);
	private static Command rotateToNeg90 = new TESTDriveRotation(-90);
	private static Command rotateToNeg45 = new TESTDriveRotation(-45);
	private static Command autoLogicMain = new AutoLogicMain();
	*/
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		/**
		 * Cameras/Vision
		 */
		//Camera Stream
		CameraServer.getInstance().startAutomaticCapture("Camera 1",0);
		//CameraServer.getInstance().startAutomaticCapture("Camera 2",1);
		
		//Vision Coordinates
		
		/**
		 * SmartDashboard
		 */
		//Auto
		/*chooser.addDefault("Cross Auto (Default)", new AutoMoveCrossAuto());
		chooser.addObject("Left, Prioritize Scale", new AutoLogicLScale());
		chooser.addObject("Left,  Scale ONLY", new AutoLogicLScaleONLY());
		chooser.addObject("Left,  Prioritize Switch", new AutoLogicLSwitch());
		chooser.addObject("Left,  Switch ONLY", new AutoLogicLSwitchONLY());
		chooser.addObject("Center, Switches", new AutoLogicCSwitch());
		chooser.addObject("Right, Prioritize Scale", new AutoLogicRScale());
		chooser.addObject("Right,  Scale ONLY", new AutoLogicRScaleONLY());
		chooser.addObject("Right, Prioritize Switch", new AutoLogicRSwitch());
		chooser.addObject("Right,  Switch ONLY", new AutoLogicRSwitchONLY());
		SmartDashboard.putData("TESTING ONLY Auto Mode", chooser);*/
		
		//Auto PROTOTYPE (Based on case switches all in one command)
		autoChooser.addDefault("Cross Auto (Default)", "CrossAuto");
		autoChooser.addObject("Prioritize Scale", "Scale");
		autoChooser.addObject("Scale ONLY", "ScaleOnly");
		autoChooser.addObject("Prioritize Switch", "Switch");
		autoChooser.addObject("Switch ONLY", "SwitchOnly");
		SmartDashboard.putData("Autonomous Mode", autoChooser);
		
		autoPosition.addDefault("Left", "Left");
		autoPosition.addObject("Center", "Center");
		autoPosition.addObject("Right", "Right");
		SmartDashboard.putData("Starting Position", autoPosition);
		
		//Choose between field and robot-oriented drive
		/* DEPRECATED. Changed from Chooser to direct boolean (below) for Shuffleboard compatibility
		driveType.addDefault("Field Oriented", true);
		driveType.addObject("Robot Oriented", false);
		SmartDashboard.putData("Drive Mode", driveType);
		*/
		SmartDashboard.putBoolean("Field Oriented?", false);
		
		//Overrides cube distance check if enabled and runs instake on button command regardless of what the LIDAR distance is.
		SmartDashboard.putBoolean("Override Intake Sensor", false);
		
		//Initiate Gyro reset
		SmartDashboard.putBoolean("Reset Gyro", false);
		
		//Switch between flightsticks and Xbox joystick
		controlType.addDefault("Dual Flightsticks", 0);
		controlType.addObject("Xbox Controller", 1);
		controlType.addObject("Daniel (Xbox)", 2);
		controlType.addObject("Hannah (Flightsticks)", 3);
		controlType.addObject("Hunter (Flightsticks)", 4);
		SmartDashboard.putData("Control Method", controlType);
		
		/**
		 * Sensor Calibration/Setup
		 */
		RobotMap.gyro.calibrate();
		RobotMap.elevatorEncoder.setDistancePerPulse(1);
		RobotMap.elevatorEncoder.reset();
		RobotMap.winchEncoder.setDistancePerPulse(1);
		RobotMap.winchEncoder.reset();
		RobotMap.wheelEncoder1.setDistancePerPulse(0.01);//TODO Set so distance = 1 inch
		RobotMap.wheelEncoder1.reset();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		SmartDashboard.putString("Autonomous Status", "DISABLED");
		isFieldOriented = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Angle", GYROSUBSYSTEM.getGyroClampedNeg180To180());
		SmartDashboard.putNumber("Elevator Encoder Distance", RobotMap.elevatorEncoder.getDistance());
		SmartDashboard.putNumber("Winch Encoder Distance", RobotMap.winchEncoder.getDistance());
		
		//If Reset Gyro button is pressed in SmartDashboard, it will calibrate the gyro. The robot MUST NOT BE MOVING. It then resets the button back to false state.
		if (SmartDashboard.getBoolean("Reset Gyro", false)) {
			RobotMap.gyro.calibrate();
			SmartDashboard.putBoolean("Reset Gyro", false);
			
			SmartDashboard.putNumber("Wheel Encoder", RobotMap.wheelEncoder1.getDistance());
		}
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//Forces drivetrain into Robot-Oriented drive for auto
		isFieldOriented = false;
		autoLogicMain.start();
		
		
		
		
		
		
		
		/*autonomousCommand = (Command) chooser.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();*/
		}
	

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoLogicMain != null) {
			autoLogicMain.cancel();
			SmartDashboard.putString("Autonomous Status", "Teleop Enabled");
			//commandTeleopDrive.start();
			
		}
		
		//Initiates command to call buttons according to the option selected on SmartDashboard (Command name = ChooseButtonLayout)
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
			Robot.xbox = new Joystick(2);
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
		
		//Links commands to buttons
		//button1 and button2 have been moved to teleopPeriodic since whileHeld didn't work here.
		buttonCubeToGround1.whenPressed(new CubeToGround());
		buttonCubeToSwitch.whenPressed(new CubeToSwitch());
		buttonCubeToScale.whenPressed(new CubeToScale());
		buttonWinchRelease.whenPressed(new WinchRelease());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//SmartDashboard data publishing
		SmartDashboard.putNumber("Gyro Angle", GYROSUBSYSTEM.getGyroClampedNeg180To180());
		SmartDashboard.putNumber("Elevator Encoder Distance", RobotMap.elevatorEncoder.getDistance());
		SmartDashboard.putNumber("Winch Encoder Distance", RobotMap.winchEncoder.getDistance());
		//SmartDashboard.putNumber("Total Amps", RobotMap.pdp.getTotalCurrent());
		
		

		
		//Changes axes according to what was selected in SmartDashboard (controlType SendableChooser)
				switch(Robot.controlType.getSelected()) {
					case 0: //General Flightsticks (Default)
						driveX = leftJoy.getRawAxis(0);
						driveY = leftJoy.getRawAxis(1);
						rotX = rightJoy.getRawAxis(0);
						povPosition = rightJoy.getPOV();
						break;
					case 1: //General Xbox
						if (Math.abs(xbox.getRawAxis(0)) > xbox360Deadzone) {driveX = xbox.getRawAxis(0); SmartDashboard.putString("Troubleshoot", "Using Xbox Joystick");} else driveX = 0;
						if (Math.abs(xbox.getRawAxis(1)) > xbox360Deadzone) driveY = xbox.getRawAxis(1); else driveY = 0;
						if (Math.abs(xbox.getRawAxis(4)) > xbox360Deadzone) rotX = xbox.getRawAxis(4); else rotX = 0;
						povPosition = xbox.getPOV();
						break;
					case 2: //Daniel
						driveX = xbox.getRawAxis(0);
						driveY = xbox.getRawAxis(1);
						rotX = xbox.getRawAxis(4);
						povPosition = xbox.getPOV();
						break;
					case 3: //Hannah
						driveX = leftJoy.getRawAxis(0);
						driveY = leftJoy.getRawAxis(1);
						rotX = rightJoy.getRawAxis(0);
						povPosition = rightJoy.getPOV();
						break;
					case 4: //Hunter
						driveX = leftJoy.getRawAxis(0);
						driveY = leftJoy.getRawAxis(1);
						rotX = rightJoy.getRawAxis(0);
						povPosition = rightJoy.getPOV();
						break;
				}
		
				
				
		
		
		//Listens for controller POV/D-pad movements, calls rotate commands based on them. Case numbers are POV/D-pad angles
		switch (povPosition) {
			case 0: 
				rotateTo0.start();
				SmartDashboard.putString("0 Setpoint?", "Oh yeah!");
				break;
			case 45:
				rotateTo45.start();
				break;
			case 90:
				rotateTo90.start();
				break;
			case 180:
				rotateTo180.start();
				break;
			case 270:
				rotateToNeg90.start();
				break;
			case 315:
				rotateToNeg45.start();
				break;
		}
		
		//Links triggers to cube functions
		//This weird format fixes the bug where the wheels would only spin for one tick, or not spin at all. Basically whileHeld in a raw format
		if(button1.get()) commandSuckCube.start(); else commandSuckCube.cancel();
		if(button2.get()) commandSpitCube.start(); else commandSpitCube.cancel();
		
		SmartDashboard.putNumber("POV Position", xbox.getPOV());
		
		//Enables SmartDashboard driveType chooser
		isFieldOriented = SmartDashboard.getBoolean("Field Oriented?", false);
		
		/**
		 * Balance Protection
		 */
		//If the elevator is raised above specified height, drivetrain speed will be reduced to quarter speed.
		//Threshold specified in "User-Defined Variables" near top
		if(RobotMap.elevatorEncoder.getDistance() > Robot.balanceProtectionElevatorHeight) driveBalance.start();
		
		//CALIBRATION ONLY
		//swerveDrive.drive(0, OI.leftJoy.getRawAxis (1), 0); //ONLY uncomment if calibrating angle encoders
		
		/**
		 * Vision Processing
		 */
		visionX = table.getEntry("X");
		visionY = table.getEntry("Y");
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		swerveDrive.drive(0, pidOutputWheel, pidOutputAngle);
	}
}
