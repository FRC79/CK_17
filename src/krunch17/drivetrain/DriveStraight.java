/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import krunch17.CommandBase;

/**
 *
 * @author Sebastián
 */
public class DriveStraight extends CommandBase {
    
    public DriveStraight(double distanceInInches) {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            drive.leftF.enableControl(0.0);
            drive.rightF.enableControl(0.0);
            drive.leftF.setX(0.0);
            drive.rightF.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        try {
            SmartDashboard.putNumber("L ENC", drive.leftF.getPosition());
            SmartDashboard.putNumber("R ENC", drive.rightF.getPosition());
            Timer.delay(0.1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        try {
            drive.leftF.disableControl();
            drive.rightF.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
