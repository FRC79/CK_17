/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.intake;

import krunch17.CommandBase;
import krunch17.RobotMap;

/**
 *
 * @author Sebastian
 */
public class RetractIntake extends CommandBase {
    
    private boolean isFinished;
    
    public RetractIntake() {
        setTimeout(intake.PISTON_DELAY);  // Wait time to finish task.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!isFinished){
            intake.retract();
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();    // Finish the task once the delay is finished
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
