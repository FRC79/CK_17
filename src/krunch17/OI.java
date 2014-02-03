
package krunch17;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick driverJoystick, shooterGamepad;
    public JoystickButton shiftButton, rollInButton, rollOutButton;
    
    public OI()
    {
        // Init Joystick and Gamepad
        driverJoystick = new Joystick(1);
        shooterGamepad = new Joystick(2);
        
        // Init joystick buttons
        shiftButton = new JoystickButton(driverJoystick, 8);
        rollInButton = new JoystickButton(driverJoystick, 11);
        rollOutButton = new JoystickButton(driverJoystick, 10);
        
    }

    public Joystick getDriverJoystick() {
        return driverJoystick;
    }

    public Joystick getShooterGamepad() {
        return shooterGamepad;
    }
    
}

