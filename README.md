# Power-Up-Robot
Team 5830's robot code for FIRST Power Up (2018). Our robot's name was Retro Rio.

## Our Robot's Features

#### Power Cubes
This year, our robot had a wheel intake attached to an elevator and winch combo, allowing it to deliver Power Cubes to the exchange, switch, and scale.

#### Swerve
This was our first year using Swerve. Although the programming behind it was very difficult for us at first, it paid off in the end, allowing us to quickly and accurately maneuver around the many obstacles in the field.

## Notable Software Features

#### Choose controller input via Shuffleboard
This is a rather uncommon feature developed by 5830 this season. At the beginning of the match, the driver can choose which preset controller layout to use, whether it be an Xbox controller, dual flightsticks, and/or a custom-built Driver Interface Device we built this year. Instead of using OI.java, we utilize two commands: JoystickMappingInit and JoystickMappingPeriodic, called in teleopInit and teleopPeriodic, respectively. In each is a switch that polls Shuffleboard to see which preset the driver selected and calls the code within as needed.

##### Advantages:
- Allows every driver to have a separate controller configuration tailored to them without the need for commenting
- Test modes can be added to the list. For example, we had a configuration that tested every piston manually.

##### Disadvantages:
- Switching while enabled causes code crash (null pointer exception)
- whileHeld doesn't work (must use `if(button.get) InstantCommand.start`)

#### Auto-rotate setpoints
We utilized the D-pad on the driver's Xbox controller to provide quick and accurate rotation setpoints using a gyroscope, allowing for quick and easy alignment with the switch and scale.

##### Advantages:
- Much quicker and more accurate than driver alignment, especially in hard-to-see places
- One less thing for the driver to do

##### Disadvantages:
- If this command is activated when the robot is positioned such that a bumper strikes an object and prevents the robot from moving, the robot will forever attempt to continue rotating while locking out the driver controls until disabled. This can be remedied using an override button (not implemented here).

#### PID-controlled elevator/wrist combo
We found that having the driver manually control motors for both the elevator and winch was just too cumbersome, so we implemented automatic PID-controlled setpoints for the elevator and winch together, activated with just one button press.

##### Advantages:
- Smooth, efficient movement
- One less thing for the driver to do
