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
    
    public DriveStraight(double distanceInInches) {
        requires(drive);
        revs = Drivetrain.distanceToRevs(distanceInInches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.resetEncoders();
        drive.turnGyro.reset(); // May need to put this in Drivetrain constructor
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double angle = drive.turnGyro.getAngle(); // get current heading
        drive.setWithCurve(1.0, -angle*kP); // Proporional compensation
        Timer.delay(400 / 1000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (drive.getAvgRevs() >= revs); // Finish when we get there
    }

    // Called once after isFinished returns true
    protected void end() {
        drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drive.stop();
    }
}