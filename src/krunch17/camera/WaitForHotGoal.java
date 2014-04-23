/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import krunch17.CommandBase;

/**
 *
 * @author Sebastian
 */
public class WaitForHotGoal extends CommandBase {

    double switchTime;
    
    public WaitForHotGoal(double switchTime) {
        this.switchTime = switchTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // Goal is hot, automatically proceed
        if(KrunchVisionServer.getInstance().getCount() > 20){
            setTimeout(0.0);
        } else {
            // Wait for the switch if the goal isn't hot yet
            setTimeout(switchTime);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
