/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.command.Command;
import krunch17.CommandBase;

/**
 *
 * @author Sebastian
 */
public class LauncherOverride extends CommandBase {
    
    Command fire, truss;
    
    public LauncherOverride(Command fire, Command truss) {
        this.fire = fire;
        this.truss = truss;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        fire.cancel();
        truss.cancel();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
