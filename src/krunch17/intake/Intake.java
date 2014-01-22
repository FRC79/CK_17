/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.intake;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import krunch17.RobotMap;

/**
 *
 * @author CBass
 */
public class Intake extends Subsystem {

    CANJaguar rollerLeft, rollerRight;
    boolean isInverted;

    public Intake(boolean isInverted){
        rollerLeft = RobotMap.rollerMotorLeft;
        rollerRight = RobotMap.rollerMotorRight;
        try {
            rollerLeft.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            rollerRight.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        this.isInverted = isInverted;
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
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
    }
    
}
