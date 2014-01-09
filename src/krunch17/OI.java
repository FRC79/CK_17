
package krunch17;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick driverJoystick, shooterGamepad;
    
    public OI()
    {
        // Init Joystick and Gamepad
        driverJoystick = new Joystick(1);
        shooterGamepad = new Joystick(2);
    }

    public Joystick getDriverJoystick() {
        return driverJoystick;
    }

    public Joystick getShooterGamepad() {
        return shooterGamepad;
    }
    
}

