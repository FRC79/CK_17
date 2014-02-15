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
public class RollIn extends CommandBase {

    boolean runsForever, isFinished;
    
    public RollIn(){
        this(false);
    }
    
    public RollIn(boolean runContinuously) {
        requires(intake);
        runsForever = runContinuously;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            intake.roller.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.setRoller(-1.0f);
        
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
