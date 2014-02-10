/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import krunch17.CommandBase;

/**
 *
 * @author Sebastian
 */
public class TestLauncher extends CommandBase {
    
    float raisePower;
    float lowerPower;
    
    public TestLauncher() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        launcher.resetEncoder();
        launcher.enableControl();
//        raisePower = (float) SmartDashboard.getNumber("RAISE POWER") / 100.0f;
//        lowerPower = (float) SmartDashboard.getNumber("LOWER POWER") / 100.0f;
        raisePower = 1.0f;
        lowerPower = -0.5f;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(oi.getDriverJoystick().getRawButton(11)){
            launcher.setMotors(raisePower);
        } else if(oi.getDriverJoystick().getRawButton(10)){
            launcher.setMotors(lowerPower);
        } else {
            launcher.stop();
        }
        
        if(launcher.isBottomStopPressed()){
            launcher.resetEncoder();
            launcher.stop();
        }
        
        if(launcher.isTopStopPressed()){
            launcher.stop();
        }
        
        SmartDashboard.putNumber("REVS", launcher.getRevs());
        SmartDashboard.putNumber("ANGLE", launcher.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.disableControl();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
