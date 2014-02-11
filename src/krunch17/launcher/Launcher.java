/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import krunch17.RobotMap;

/**
 *
 * @author Sebastian
 */
public class Launcher extends Subsystem {

    public static final double GEAR_RATIO = 42.22;
    public static final int TICS_PER_REV = (int)(250 * GEAR_RATIO);
    public static final double LAUNCH_DELAY = 0.50;
    
    public static final float LOWER_POWER = -0.5f;
    public static final float RAISE_POWER = 1.0f;
    
    private double encoderOffset;
    
    CANJaguar motorL, motorR;
    DigitalInput topStop, bottomStop;
    
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
    
        bottomStop = RobotMap.bottomStop;
        topStop = RobotMap.topStop;
        
        encoderOffset = 0.0;
    }
    
    public void setMotors(float power){
        if(isBottomStopPressed() && power < 0.0f){
            power = 0.0f;
        }
        
        if(isTopStopPressed() && power > 0.0f){
            power = 0.0f;
        }
        
        motorL.set(-power);
        motorR.set(power);
    }
    
    public void stop(){
        setMotors(0.0f);
    }
    
    public double getRevs(){
        try {
            return Math.abs(motorR.getPosition()) - encoderOffset;    
                    
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }
    
    public double getAngle(){
        return getRevs() * 360.0;
    }
    
    public void resetEncoder(){
        encoderOffset += getRevs();
    }
    
    public void enableControl(){
        try {
            motorL.enableControl();
            motorR.enableControl(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void disableControl(){
        try {
            motorL.disableControl();
            motorR.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isBottomStopPressed(){
        return bottomStop.get();
    }
    
    public boolean isTopStopPressed(){
        return topStop.get();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StowLauncher());
    }
}
