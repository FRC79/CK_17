/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.launcher;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.intake.ExtendIntake;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class LightPassAutomated extends CommandGroup {
    
    public static float PASS_RAISE_POWER = 0.6f;
    
    public LightPassAutomated() {
        addSequential(new Wait(0.004));
        addSequential(new ExtendIntake());
        addSequential(new Wait(0.5 + 0.0)); // Truss doesn't need ball to settle
        addSequential(new FireLauncher(PASS_RAISE_POWER)); // Launcher will retract after fire
    }
}
