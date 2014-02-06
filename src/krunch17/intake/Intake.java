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
    
    CANJaguar roller;
    DoubleSolenoid piston;

    public static final double PISTON_DELAY = 0.50;

    boolean isInverted, isExtended;

    public Intake(boolean isInverted){
        this.isInverted = isInverted;
        isExtended = kRetracted;
        
        roller = RobotMap.rollerMotors;
        piston = RobotMap.intakeExtenderPiston;
        
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
        float invertCoef = isInverted ? -1.0f : 1.0f;
        roller.set(invertCoef * power);
    }       
            
    public void stopRoller(){
        setRoller(0.0f);
    }
    
    public void setPiston(boolean state) {
        piston.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }
    
    // Sets the state variable after delay has happened
    public void setExtendedStateVariable(boolean state){
        isExtended = state;
    }
    
    public boolean isExtended(){
        return isExtended;
    }
    
    public void extend(){
        setPiston(kExtended);
    }
    
    public void retract(){
        setPiston(kRetracted);
    }
    
    public void invertPiston(){
        setPiston(!isExtended());
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StopRollerMotors(true));
    }
    
}
