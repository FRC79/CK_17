/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krunch17.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import krunch17.drivetrain.DriveStraight;

/**
 *
 * @author Sebastian
 */
public class SimpleDrive extends CommandGroup {
    
    public SimpleDrive() {
        // Maybe wait a bit for the pneumatics to kick in
        addSequential(new DriveStraight(6 * 12)); // Drive for 4.5 feet 
    }
}
