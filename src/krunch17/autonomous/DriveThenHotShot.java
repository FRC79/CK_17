/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.camera.WaitForHotGoal;
import krunch17.drivetrain.DriveStraight;
import krunch17.intake.ExtendIntake;
import krunch17.launcher.FireLauncher;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class DriveThenHotShot extends CommandGroup {
    
    public DriveThenHotShot() {
        addSequential(new DriveStraight(4.5 * 12)); // Drive 4.5 feet
        addSequential(new Wait(0.5)); // Wait to stop completely
        addSequential(new ExtendIntake()); // Will extend first and then procede
        addSequential(new WaitForHotGoal(4.0)); // With a 4 second switch time
        addSequential(new FireLauncher()); // Will retract after fire
    }
}
