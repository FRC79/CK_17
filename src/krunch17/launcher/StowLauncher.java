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
public class StowLauncher extends CommandBase {
    
    public StowLauncher() {
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        launcher.enableControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // If the bottom stop is pressed, stop the motors and reset encoder
        if(launcher.isBottomStopPressed()){
            launcher.resetEncoder();
            launcher.stop();
            intake.setIsLockedToExtend(false); // Let intake move again
        } else {
            launcher.setMotors(launcher.LOWER_POWER);
            intake.extendPiston(); // Extend it out of the way
            intake.setIsLockedToExtend(true); // Get intake out of the way
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
