/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.util;

import edu.wpi.first.wpilibj.command.Command;
import krunch17.CommandBase;

/**
 *
 * @author Sebastian
 */
public class CancelCommand extends CommandBase {
    
    Command command;
    
    public CancelCommand(Command command) {
        this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        command.cancel();
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
