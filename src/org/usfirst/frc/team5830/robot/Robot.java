/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5830.robot;

import org.usfirst.frc.team5830.robot.commands.AutoLogic;
import org.usfirst.frc.team5830.robot.commands.DriveBalance;
import org.usfirst.frc.team5830.robot.commands.DriveRotationSetpoint;
import org.usfirst.frc.team5830.robot.commands.DriveStraight;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingInit;
import org.usfirst.frc.team5830.robot.commands.JoystickMappingPeriodic;
import org.usfirst.frc.team5830.robot.commands.SuckCube;
import org.usfirst.frc.team5830.robot.commands.TeleopSpitCube;
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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
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
	//Xbox controller stick deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxStickDeadzone = 0.1;
	//Xbox controller trigger deadzone size. 1 is entire range, 0 is disabled, closer to zero means less deadzone
	public static final double xboxTriggerDeadzone = 0.2;
	//Distance from LIDAR cube has to be to switch intake from sucking to spitting
	public static final double cubeDistance = 9.5; //Inches
	//Maximum elevator speed up
	public static final double maxElevatorSpeedUp = 1; //Between 0 and 1. NEGATIVE NUMBERS WILL NOT WORK!
	//Maximum elevator speed up
	public static final double maxElevatorSpeedDown = -0.75; //Between -1 and 0. POSITIVE NUMBERS WILL NOT WORK!
	
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
	public static Button buttonPortalL;
	public static Button buttonPortalR;
	
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
	
	//Vision Processing (For future use)
	/*public NetworkTableEntry visionX;
	public NetworkTableEntry visionY;
	private NetworkTableInstance inst = NetworkTableInstance.getDefault();
	private NetworkTable table = inst.getTable("grip");*/
	
	/**
	 * Subsystems
	 */
	
	//Regular
	public static final PowerCube POWERCUBE = new PowerCube();
	
	//LIDAR
	public static final LIDARSubsystem lidarSubsystem = new LIDARSubsystem();
	
	//PID Loops
	public static final PIDLIDARDistance auto_LIDAR_Distance_Swerve = new PIDLIDARDistance();
	public static final PIDRotationCorrection pidROTATIONCORRECTION = new PIDRotationCorrection();
	public static final PIDElevator ELEVATORPID = new PIDElevator();
	public static final PIDWinch WINCHPID = new PIDWinch();
	public static final PIDWheelDistance WHEELDISTANCEPID = new PIDWheelDistance();
	public static final GyroSubsystem GYROSUBSYSTEM = new GyroSubsystem();
	
	/**
	 * Commands
	 */
	private static Command joystickMappingInit = new JoystickMappingInit();
	private static Command joystickMappingPeriodic = new JoystickMappingPeriodic();
	public static Command commandSuckCube = new SuckCube();
	public static Command commandSpitCube = new TeleopSpitCube();
	private static Command driveBalance = new DriveBalance();
	public static Command rotateTo0 = new DriveRotationSetpoint(0);
	public static Command rotateTo45 = new DriveRotationSetpoint(45);
	public static Command rotateTo90 = new DriveRotationSetpoint(90);
	public static Command rotateTo180 = new DriveRotationSetpoint(180);
	public static Command rotateToNeg90 = new DriveRotationSetpoint(-90);
	public static Command rotateToNeg45 = new DriveRotationSetpoint(-45);
	private static Command autoLogicMain = new AutoLogic();
	
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
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(640, 480);
		camera.setFPS(30);
		
		//Vision Coordinates
		
		/**
		 * SmartDashboard
		 */		
		//Autonmous Mode
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
		SmartDashboard.putBoolean("Field Oriented?", false);
		
		//Overrides cube distance check if enabled and runs instake on button command regardless of what the LIDAR distance is.
		SmartDashboard.putBoolean("Override Intake Sensor", false);
		
		//Initiate Gyro reset
		SmartDashboard.putBoolean("Reset Sensors", false);
		
		//Switch between flightsticks and Xbox joystick
		controlType.addDefault("Dual Flightsticks", 0);
		controlType.addObject("Xbox Controller", 1);
		controlType.addObject("Daniel (Xbox)", 2);
		controlType.addObject("Hannah (Flightsticks)", 3);
		controlType.addObject("Hunter (Flightsticks)", 4);
		controlType.addObject("Arcade Flightstick", 5);
		SmartDashboard.putData("Control Method", controlType);
		
		//Displays whether or not Balance Protection is enabled via a color changing "Boolean Box" in Shuffleboard
		SmartDashboard.putBoolean("Balance Protection enabled?", false);
		
		//Lets the driver know which autonomous path was chosen automatically
		SmartDashboard.putString("Autonomous Path Chosen", "Waiting for Match Start");
		
		//Shows current robot command running
		SmartDashboard.putString("Status", "Waiting for Match Start");
		
		//Troubleshooting Posts - visible in tab 2 of shuffleboard, made for fast and easy logic troubleshooting
		SmartDashboard.putString("Troubleshoot - String", "null");
		//SmartDashboard.putBoolean("Troubleshoot - Boolean", false); //Commented out to avoid confusion with an actual "false" troubleshooting step
		SmartDashboard.putNumber("Troubleshoot - Number", 0);
		
		/**
		 * Sensor Calibration/Setup
		 */
		RobotMap.gyro.calibrate();
		RobotMap.elevatorEncoder.setDistancePerPulse(1);
		RobotMap.elevatorEncoder.reset();
		RobotMap.winchEncoder.setDistancePerPulse(1);
		RobotMap.winchEncoder.reset();
		RobotMap.wheelEncoder1.setDistancePerPulse(0.0965989132622258);
		RobotMap.wheelEncoder1.reset();

	}

	@Override
	public void disabledInit() {
		SmartDashboard.putString("Status", "Waiting for Match Start");
		SmartDashboard.putString("Autonomous Path Chosen", "Waiting for Match Start");
		isFieldOriented = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Angle", GYROSUBSYSTEM.getGyroClampedNeg180To180());
		SmartDashboard.putNumber("Elevator Encoder Distance", RobotMap.elevatorEncoder.getDistance());
		SmartDashboard.putNumber("Winch Encoder Distance", RobotMap.winchEncoder.getDistance());
		
		//If Reset Sensors button is pressed in SmartDashboard, it will calibrate the gyro. The robot MUST NOT BE MOVING. It then resets the button back to false state.
		if (SmartDashboard.getBoolean("Reset Sensors", false)) {
			RobotMap.gyro.calibrate();
			RobotMap.elevatorEncoder.reset();
			RobotMap.wheelEncoder1.reset();
			RobotMap.winchEncoder.reset();
			SmartDashboard.putBoolean("Reset Sensors", false);
			SmartDashboard.putNumber("Wheel Encoder", RobotMap.wheelEncoder1.getDistance());
		}
		
		SmartDashboard.putBoolean("Troubleshoot - Boolean", DriveStraight.isItFinished);
		
	}
	
	@Override
	public void autonomousInit() {
		//Forces drivetrain into Robot-Oriented drive for auto
		isFieldOriented = false;
		autoLogicMain.start();
		}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Troubleshoot - Boolean", DriveStraight.isItFinished);
		}

	@Override
	public void teleopInit() {
		if (autoLogicMain != null) {
			autoLogicMain.cancel();
		}
		
		SmartDashboard.putString("Status", "Teleop Driving");
		
		//Takes ShuffleBoard button layout presets and maps buttons accordingly
		joystickMappingInit.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//Processes axis values
		joystickMappingPeriodic.start();
		
		//SmartDashboard data publishing
		
		//Enables SmartDashboard driveType chooser
		isFieldOriented = SmartDashboard.getBoolean("Field Oriented?", false);
		
		/**
		 * Balance Protection
		 */
		//If the elevator is raised above specified height, drivetrain speed will be reduced to quarter speed.
		//Threshold specified in "User-Defined Variables" near top
		if(RobotMap.elevatorEncoder.getDistance() > Robot.balanceProtectionElevatorHeight) {
			driveBalance.start();
			SmartDashboard.putBoolean("Balance Protection enabled?", true);//Changes color of "Bal. Protection?" boolean box in Shuffleboard to notify the driver
		} else {
			SmartDashboard.putBoolean("Balance Protection enabled?", false);
		}
		
		/**
		 * Vision Processing (For future use)
		 */
		//visionX = table.getEntry("X");
		//visionY = table.getEntry("Y");
	}

	@Override
	public void testPeriodic() {
		SmartDashboard.putString("Status", "TEST MODE");
		swerveDrive.drive(0, pidOutputWheel, pidOutputAngle);
	}
}
