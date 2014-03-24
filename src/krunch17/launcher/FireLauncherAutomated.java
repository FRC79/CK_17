/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.autonomous.AutonSettings;
import krunch17.intake.ExtendIntake;
import krunch17.intake.RollIn;
import krunch17.intake.StopRoller;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class FireLauncherAutomated extends CommandGroup {
    
    public FireLauncherAutomated() {
        addSequential(new Wait(0.004));
        addSequential(new ExtendIntake());
        addSequential(new Wait(AutonSettings.EXTEND_INTAKE_DELAY + AutonSettings.FIRE_DELAY));
        addSequential(new FireLauncher()); // Launcher will retract after fire
    }
}
