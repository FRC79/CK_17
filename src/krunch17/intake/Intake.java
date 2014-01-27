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

    public static final double PISTON_DELAY = 0.50;
    
    CANJaguar rollerLeft, rollerRight;
    DoubleSolenoid armA, armB;
    boolean isInverted, isExtended;

    public Intake(boolean isInverted){
        this.isInverted = isInverted;
        isExtended = false;
        
        rollerLeft = RobotMap.rollerMotorLeft;
        rollerRight = RobotMap.rollerMotorRight;
        armA = RobotMap.intakeArmA;
        armB = RobotMap.intakeArmB;
        
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
    
    public void setArm(boolean state) {
        armA.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        armB.set(!state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        isExtended = state;
    }
    
    public boolean isExtended(){
        return isExtended;
    }
    
    public void extend(){
        setArm(true);
    }
    
    public void retract(){
        setArm(false);
    }
    
    public void invertArmState(){
        setArm(!isExtended());
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StopRollerMotors());
    }
    
}
