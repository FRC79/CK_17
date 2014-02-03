
package krunch17;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import krunch17.drivetrain.ArcadeDrive;
import krunch17.drivetrain.DriveStraight;
import krunch17.drivetrain.ShiftToHighGear;
import krunch17.drivetrain.ShiftToInverted;
import krunch17.intake.RollIn;
import krunch17.intake.RollOut;
import krunch17.intake.StopRollerMotors;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CK_17 extends IterativeRobot {

    Command autonomousCommand, arcadeDriveCommand, initialShiftCommand,
            toggleShiftCommand, rollInCommand, rollOutCommand, stopRollerCommand;

    public void robotInit() {
        // Initialize all subsystems
        RobotMap.init();
        CommandBase.init();
        
        // instantiate commands
//        autonomousCommand = new DriveStraight();
        arcadeDriveCommand = new ArcadeDrive();
        initialShiftCommand = new ShiftToHighGear();
        toggleShiftCommand = new ShiftToInverted();
        rollInCommand = new RollIn();
        rollOutCommand = new RollOut();
        stopRollerCommand = new StopRollerMotors();
        
        // Map commands to buttons
        CommandBase.oi.shiftButton.whenPressed(toggleShiftCommand);
        CommandBase.oi.rollInButton.whenPressed(rollInCommand);
        CommandBase.oi.rollOutButton.whenPressed(rollOutCommand);
        CommandBase.oi.rollInButton.whenReleased(stopRollerCommand);
        CommandBase.oi.rollOutButton.whenReleased(stopRollerCommand);
        
        System.out.println("--------------------------------------");
        System.out.println("  robotInit() COMPLETE ");
        System.out.println("--------------------------------------");
    }

    public void autonomousInit() {
//        RobotMap.compressor.start(); // Start compressor
        initialShiftCommand.start();
//        autonomousCommand.start(); // schedule the autonomous command
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//        autonomousCommand.cancel(); // Make sure auton is finished
//        RobotMap.compressor.start(); // Start compressor
        initialShiftCommand.start();
        arcadeDriveCommand.start(); // Start teleop arcade drive
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    
    public void disabledInit() {
        Scheduler.getInstance().removeAll(); // Stop all commands
//        RobotMap.compressor.stop(); // Stop compressor
    }

    public void testInit() {
//        RobotMap.compressor.start();  // Start compressor
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
