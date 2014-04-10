/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.Timer;
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
                intake.setIsLockedToExtend(true); // Get intake out of the way
                if(!intake.isExtended()){
                    intake.extendPiston(); // Extend it out of the way
//                    Timer.delay(intake.PISTON_DELAY);
                    intake.setExtendedStateVariable(intake.kExtended);
                }
                launcher.setMotors(launcher.RAISE_POWER);
            } else {
                launcher.stop();
//                Timer.delay(launcher.FIRING_DELAY);
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
