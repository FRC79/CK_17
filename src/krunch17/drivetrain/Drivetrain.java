/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import krunch17.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Drivetrain extends Subsystem {

    CANJaguar frontL, frontR, rearL, rearR;
    RobotDrive drive;
    
    public Drivetrain(){
        frontL = RobotMap.frontLeftWheel;
        frontR = RobotMap.frontRightWheel;
        rearL = RobotMap.rearLeftWheel;
        rearR = RobotMap.rearRightWheel;
        drive = RobotMap.robotDrive;
    }
    
    public void arcadeDrive(float moveVal, float rotVal){
        drive.arcadeDrive(moveVal, rotVal);
    }
    
    public void setLeftAndRightMotorOutputs(float powerLeft, float powerRight){
        drive.setLeftRightMotorOutputs(powerLeft, powerRight);
    }
    
    public void set(float power){
        setLeftAndRightMotorOutputs(power, power);
    }
    
    public void stop(){
        set(0.0f);
    }
    
    public void initDefaultCommand() {
        /* We want the drivetrain to stop when we don't send it values to
         * prevent CAN timeout errors. */
        setDefaultCommand(new StopDriveMotors()); // May need to add false to run continuously
    }
}
