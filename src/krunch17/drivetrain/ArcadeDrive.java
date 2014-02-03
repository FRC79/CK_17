/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import krunch17.CommandBase;
import krunch17.util.TeleopHelper;

/**
 *
 * @author sebastian
 */
public class ArcadeDrive extends CommandBase {
    
    public ArcadeDrive() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Get the raw axis data
        double rawMoveAxis = oi.getDriverJoystick().getRawAxis(2);
        double rawRotAxis = oi.getDriverJoystick().getRawAxis(1);
        
        double moveOutput = 0.0;
        double rotOutput = 0.0;
        
        // Pass it through the mapping equation to add driver precision
        if(drive.getShiftState() == Drivetrain.kHigh_Gear){
            moveOutput = TeleopHelper.mJ2POutHighGearMov(rawMoveAxis);
            rotOutput = TeleopHelper.mJ2POutHighGearRot(rawRotAxis);
            
        } else if(drive.getShiftState() == Drivetrain.kLow_Gear){
            moveOutput = TeleopHelper.mJ2POutLowGearMov(rawMoveAxis);
            rotOutput = TeleopHelper.mJ2POutLowGearRot(rawRotAxis);
        }
        
        // Set the drivetrain motors to those corresponding values
        drive.arcadeDrive((float)moveOutput, (float)rotOutput);
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
