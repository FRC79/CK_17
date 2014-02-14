/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.drivetrain.DriveStraight;
import krunch17.launcher.FireLauncher;
import krunch17.util.Wait;

/**
 *
 * @author Sebastian
 */
public class SimpleDriveThenShot extends CommandGroup {
    
    public SimpleDriveThenShot() {
        addSequential(new DriveStraight(6 * 12)); // Drive 4.5 feet
        addSequential(new Wait(0.5)); // Wait to stop completely
        addSequential(new FireLauncher()); // Launcher will retract after fire
    }
}
