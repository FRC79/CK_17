/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import krunch17.CommandBase;

/**
 *
 * @author Sebastián
 */
public class DriveStraight extends CommandBase {
    
    double revs;
    double kP = 0.03;
    
    public DriveStraight(double time) {
        requires(drive);
        setTimeout(time);
//        revs = Drivetrain.distanceToRevs(distanceInInches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.resetEncoders();
        drive.setDriveControlsInverted(false);
//        drive.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        double angle = drive.getAngle(); // get current heading
//        drive.setWithCurve(1.0, -angle*kP); // Proporional compensation
//        Timer.delay(400 / 1000);
        drive.set(1.0f);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();//(drive.getAvgRevs() >= revs); // Finish when we get there
    }

    // Called once after isFinished returns true
    protected void end() {
        drive.set(0.75f);
        System.out.println("STOPPING");
        Timer.delay(0.125);
        drive.set(0.5f);
        Timer.delay(0.125);
        drive.setLandR(0.25f, 0.5f);
        Timer.delay(0.125);
        drive.stop();
        System.out.println("STOPPED");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drive.stop();
    }
}