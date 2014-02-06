/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.intake;

import krunch17.CommandBase;

/**
 *
 * @author kijan_000
 */
public class InvertIntake extends CommandBase {
    
    private boolean isFinished;
    
    public InvertIntake() {
        setTimeout(intake.PISTON_DELAY); // Wait time to finish task.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!isFinished){
            intake.invertPiston();
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); // Wait for the timeout in order to end the task.
    }

    // Called once after isFinished returns true
    protected void end() {
        intake.setExtendedStateVariable(!intake.isExtended());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
