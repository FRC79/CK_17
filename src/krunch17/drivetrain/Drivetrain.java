/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import krunch17.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Drivetrain extends Subsystem {

    static final boolean kHigh_Gear = false;
    static final boolean kLow_Gear = true;
    
    public static final double GEAR_RATIO = 0.8;
    public static final int TICS_PER_REV = (int)(250.0 * GEAR_RATIO);
    public static final double CIRCUMFERENCE = 4.0 * Math.PI; // Inches
    
    private boolean driveControlsInverted;
    private boolean shiftState;
    private double encoderOffset;
    private double angleOffset;
    public static boolean TELEOP_CONTROL_DISABLED = false;

    
    CANJaguar leftF, rightF, leftR, rightR;
    RobotDrive robotDrive;
    DoubleSolenoid shifter;
    
    public Gyro turnGyro;
    
    public Drivetrain(){
        // Create local references to motors
        leftF = RobotMap.leftFrontMotor;
        rightF = RobotMap.rightFrontMotor;
        leftR = RobotMap.leftRearMotor;
        rightR = RobotMap.rightRearMotor;
        
        driveControlsInverted = false;
        
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
        
        // Init turn gyro
        turnGyro = RobotMap.turnGyro;
        
        encoderOffset = 0.0;
    }
    
    
    
    
    public boolean areDriveControlsInverted(){
        return driveControlsInverted;
    }
    
    public void setDriveControlsInverted(boolean state){
        driveControlsInverted = state;
    }
    
    
    
    
    public void setWithCurve(double power, double curve){
        robotDrive.drive(power, curve);
    }
    
    public void arcadeDrive(float moveVal, float rotVal){
        if(TELEOP_CONTROL_DISABLED){
            moveVal = 0f;
            rotVal = 0f;
        }
        
        float invMoveVal = (!driveControlsInverted) ? -moveVal : moveVal;
        robotDrive.arcadeDrive(invMoveVal, rotVal, true); // Decrease sensitivity when true
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
    
    public double getAngle(){
        return turnGyro.getAngle() - angleOffset;
    }
    
    public void resetGyro(){
        angleOffset += (turnGyro.getAngle() - angleOffset);
    }
    
    public double getAvgRevs(){
          try {
              double revsLeft = Math.abs(leftF.getPosition());
              double revsRight = Math.abs(rightF.getPosition());
              return ((revsLeft + revsRight) / 2) - encoderOffset;
          } catch (CANTimeoutException ex) {
              ex.printStackTrace();
              return 0.0;
          }
     }
      
     public void resetEncoders(){
           encoderOffset += getAvgRevs();
      }
      
     public static double distanceToRevs(double distanceInInches){
          return distanceInInches / CIRCUMFERENCE;
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
