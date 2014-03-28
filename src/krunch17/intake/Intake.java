/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.intake;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import krunch17.RobotMap;

/**
 *
 * @author CBass
 */
public class Intake extends Subsystem {

    public static final boolean kExtended = true;
    public static final boolean kRetracted = false;
    public static final double PISTON_DELAY = 1.00;
    public static final float ROLL_POWER = 1.0f;
    
    boolean isInverted, isExtended;
    boolean isLockedToExtend;
    
    CANJaguar roller;
    DoubleSolenoid piston;

    public Intake(boolean isInverted){
        this.isInverted = isInverted;
        isExtended = kRetracted;
        
        roller = RobotMap.rollerMotors;
        piston = RobotMap.intakePiston;
        
        isLockedToExtend = false;
        
        try {
            roller.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public Intake(){
        this(false);
    }
            
    public void setRollerInverted(boolean state){
        isInverted = state;
    }
        
    public void setRoller(float power){
        float invertCoef = isInverted ? -ROLL_POWER : ROLL_POWER;
        roller.set(invertCoef * power);
    }       
            
    public void stopRoller(){
        setRoller(0.0f);
    }
    
    public void setPiston(boolean state) {
        if(isLockedToExtend){
            state = kExtended;
        }
        
        piston.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    
        System.out.println("-----------------------------------------");
        System.out.println("SETTING PISTON TO A DIFFERENT THING");
        System.out.println("-----------------------------------------");
    }
    
    // Sets the state variable after delay has happened
    public void setExtendedStateVariable(boolean state){
        isExtended = state;
    }
    
    public boolean isExtended(){
        return isExtended;
    }
    
    public void extendPiston(){
        setPiston(kExtended);
    }
    
    public void retractPiston(){
        setPiston(kRetracted);
    }
    
    public void invertPiston(){
        setPiston(!isExtended());
    }
    
    public void setIsLockedToExtend(boolean state){
        isLockedToExtend = state;
    }
    
    public boolean getIsLockedToExtend(){
        return isLockedToExtend;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StopRoller(true));
    }
    
}
