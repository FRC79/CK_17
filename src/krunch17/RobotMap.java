package krunch17;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static CANJaguar leftFrontMotor, rightFrontMotor, leftRearMotor,
            rightRearMotor, rollerMotors, leftLauncherMotor, rightLauncherMotor; 
    public static RobotDrive robotDrive;
    public static Compressor compressor;
    public static DoubleSolenoid sonicShifter, intakePiston;
    public static DigitalInput bottomStop, topStop;
    
    public static void init(){
        loadComponents(true); // Used to init CSVs and all components
    }
    
    public static void reload(){
        loadComponents(false); // Reload CSV values without reiniting CSVReaders.
    }
    
    private static void loadComponents(boolean initCSVs)
    {
        
        try {
            // Init Drive Wheels
            leftFrontMotor = new CANJaguar(2);
            leftRearMotor = new CANJaguar(3);
            rightFrontMotor = new CANJaguar(4);
            rightRearMotor = new CANJaguar(5);

            // Init Roller motor
            rollerMotors = new CANJaguar(6);
            
            // Init Launcher Motors
            leftLauncherMotor = new CANJaguar(7);
            rightLauncherMotor = new CANJaguar(8);
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
               
        // Init Compressor (pressureSwitchChannel, compressorRelayChannel)
//        compressor = new Compressor(1,1);

        // Init Solenoids (forwardChannel, reverseChannel) 
//        sonicShifter = new DoubleSolenoid(1,2);
//        intakePiston = new DoubleSolenoid(3,4);
        
        bottomStop = new DigitalInput(3);
        topStop = new DigitalInput(2);
    }
}
