package krunch17;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
    public static CANJaguar frontLeftWheel, frontRightWheel, rearLeftWheel,
            rearRightWheel;
    public static RobotDrive robotDrive;
    
    public static void init(){
        loadComponents(true); // Used to init CSVs and all components
    }
    
    public static void reload(){
        loadComponents(false); // Reload CSV values without reiniting CSVReaders.
    }
    
    private static void loadComponents(boolean initCSVs)
    {
        // Init Drive Wheels
        try {
            frontLeftWheel = new CANJaguar(1);
            frontRightWheel = new CANJaguar(2);
            rearLeftWheel = new CANJaguar(3);
            rearRightWheel = new CANJaguar(4);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        // Init RobotDrive
        robotDrive = new RobotDrive(frontLeftWheel, rearLeftWheel, 
                frontRightWheel, rearRightWheel);
//        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
//        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
//        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
//        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
}
