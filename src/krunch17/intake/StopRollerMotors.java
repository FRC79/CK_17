/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.intake;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import krunch17.CommandBase;

/**
 *
 * @author kijan_000
 */
public class StopRollerMotors extends CommandBase {
    
    boolean isFinished, runsForever;
    
    public StopRollerMotors(){
        this(false);
    }
    
    public StopRollerMotors(boolean runContinuously){
        requires(intake);
        isFinished = false;
        runsForever = runContinuously;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            intake.rollerLeft.enableControl();
            intake.rollerRight.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.stopRoller();
        
        if(!runsForever){
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
        try {
            intake.rollerLeft.disableControl();
            intake.rollerRight.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
