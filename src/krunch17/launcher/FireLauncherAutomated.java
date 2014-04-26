/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.intake.ExtendIntake;
import krunch17.intake.RollIn;
import krunch17.intake.StopRoller;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class FireLauncherAutomated extends CommandGroup {
    
    public static float HIGH_GOAL_RAISE_POWER = 0.92f;
    
    public FireLauncherAutomated() {
        addSequential(new Wait(0.004));
        addSequential(new ExtendIntake());
        addSequential(new Wait(0.5 + 0.5));
        addSequential(new FireLauncher(HIGH_GOAL_RAISE_POWER)); // Launcher will retract after fire
    }
}
