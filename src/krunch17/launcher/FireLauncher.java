/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import krunch17.CommandBase;

/**
 *
 * @author Sebastian
 */
public class FireLauncher extends CommandBase {
    
    boolean initiallyCanceled, isFinished;
    
    public FireLauncher() {
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // We only want to fire if we are all the way at the bottom
        initiallyCanceled = !launcher.isBottomStopPressed();
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!initiallyCanceled){
            
            if(!launcher.isTopStopPressed() && !launcher.encoderLimitReached()){
                launcher.setMotors(launcher.RAISE_POWER);
            } else {
                launcher.stop();
                isFinished = true;
            }
            
        } else {
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
