/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.camera.CheckForHotGoal;
import krunch17.camera.WaitForHotGoal;
import krunch17.drivetrain.DriveStraight;
import krunch17.intake.ExtendIntake;
import krunch17.intake.RollIn;
import krunch17.intake.StopRoller;
import krunch17.launcher.FireLauncher;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class DriveThenHotShot extends CommandGroup {
    
    public DriveThenHotShot() {
        addSequential(new CheckForHotGoal());
        addSequential(new DriveStraight(1.0)); // Drive for time
        addSequential(new Wait(0.5)); // Wait to stop completely
        addSequential(new ExtendIntake());
        addSequential(new Wait(0.5));
        addSequential(new WaitForHotGoal(3.0));
        addSequential(new FireLauncher()); // Launcher will retract after fire
    }
}
