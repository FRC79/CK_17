/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import krunch17.RobotMap;

/**
 *
 * @author Sebastian
 */
public class Launcher extends Subsystem {

    public static final double GEAR_RATIO = 16.5;
    public static final int TICS_PER_REV = (int)(250 * GEAR_RATIO);
    public static final double LAUNCH_DELAY = 0.50;
    
    CANJaguar motorL, motorR;
    
    public Launcher(){
        // Init local variables
        motorL = RobotMap.leftLauncherMotor;
        motorR = RobotMap.rightLauncherMotor;
        
        try {
            // Configure Jaguars
            motorR.configEncoderCodesPerRev(TICS_PER_REV);
            
            motorR.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            
            motorL.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            motorR.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setMotors(float power){
        motorL.set(-power);
        motorR.set(power);
    }
    
    public void stop(){
        setMotors(0.0f);
    }
    
    public double getRevs(){
        try {
            return motorR.getPosition();    
                    
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }
    
    public double getAngle(){
        return getRevs() * 360.0;
    }
    
    public void enableMotors(){
        try {
            motorL.enableControl();
            motorR.enableControl(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enableMotors(double initialEncoderPosition){
        try {
            motorL.enableControl(initialEncoderPosition);
            motorR.enableControl(initialEncoderPosition);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void disableMotors(){
        try {
            motorL.disableControl();
            motorR.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initDefaultCommand() {
//        setDefaultCommand(new StoreLauncher());
    }
}
