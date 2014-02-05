/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import krunch17.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Drivetrain extends Subsystem {

    public static final int TICS_PER_REV = 250;
    
    static final boolean kHigh_Gear = false;
    static final boolean kLow_Gear = true;
    
    private boolean shiftState;
    
    CANJaguar leftF, rightF, leftR, rightR;
    RobotDrive robotDrive;
    DoubleSolenoid shifter;
    
    public Drivetrain(){
        // Create local references to motors
        leftF = RobotMap.leftFrontMotor;
        rightF = RobotMap.rightFrontMotor;
        leftR = RobotMap.leftRearMotor;
        rightR = RobotMap.rightRearMotor;
        
        // Init RobotDrive
        robotDrive = new RobotDrive(leftF, leftR, rightF, rightR);
        
        try {
            // Setup encoders
            leftF.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            rightF.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            
            leftF.configEncoderCodesPerRev(TICS_PER_REV);
            rightF.configEncoderCodesPerRev(TICS_PER_REV);
            
            leftF.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            rightF.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        // Init shifter
        shifter = RobotMap.sonicShifter;
    }
    
    public void arcadeDrive(float moveVal, float rotVal){
        robotDrive.arcadeDrive(moveVal, rotVal);
    }
    
    public void setLandR(float powerLeft, float powerRight){
        robotDrive.setLeftRightMotorOutputs(powerLeft, powerRight);
    }
    
    public void set(float power){
        setLandR(power, power);
    }
    
    public void stop(){
        set(0.0f);
    }
    
    public void shift(boolean gearSetting){
        shifter.set(gearSetting ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        shiftState = gearSetting;
    }
    
    public void shiftInverted(){
        shift(!shiftState);
    }
    
    public boolean getShiftState(){
        return shiftState;
    }
    
    public void initDefaultCommand() {
        /* We want the drivetrain to stop when we don't send it values to
         * prevent CAN timeout errors. */
        setDefaultCommand(new StopDriveMotors(true));
    }
}
