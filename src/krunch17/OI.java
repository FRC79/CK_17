
package krunch17;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick driverJoystick, shooterGamepad;
    public JoystickButton shiftButton, invertArcadeDriveButton, 
            toggleIntakeExtensionButton;
    
    public OI()
    {
        // Init Joystick and Gamepad
        driverJoystick = new Joystick(1);
        shooterGamepad = new Joystick(2);
        
        // Init joystick buttons
        shiftButton = new JoystickButton(driverJoystick, 7);
        invertArcadeDriveButton = new JoystickButton(driverJoystick, 8);
        
        toggleIntakeExtensionButton = new JoystickButton(shooterGamepad, 7);
    }
    
    public float getDPadAxisY(Joystick refJoystick){
        double val = refJoystick.getRawAxis(5); // Get DPad axis
    
        // Scale the input like binary buttons
        if(val > 0.5){
            return 1.0f;
        } else if(val < -0.5){
            return -1.0f;
        } else {
            return 0.0f;
        }
    }

    public Joystick getDriverJoystick() {
        return driverJoystick;
    }

    public Joystick getManipGamepad() {
        return shooterGamepad;
    }
    
}

