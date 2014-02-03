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
    
    CANJaguar rollerLeft, rollerRight;
    DoubleSolenoid piston;
    boolean isInverted, isExtended;

    public Intake(boolean isInverted){
        this.isInverted = isInverted;
        isExtended = kRetracted;
        
        rollerLeft = RobotMap.rollerMotorLeft;
        rollerRight = RobotMap.rollerMotorRight;
        piston = RobotMap.intakeExtenderPiston;
        
        try {
            rollerLeft.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            rollerRight.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
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
        float invertCoef = isInverted ? -1 : 1;
        float output = invertCoef * power;
        try {
            rollerLeft.setX(output);
            rollerRight.setX(output * -1.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
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
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
    }
    
}
