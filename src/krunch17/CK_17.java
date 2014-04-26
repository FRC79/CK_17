
package krunch17;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import krunch17.autonomous.DoNothing;
import krunch17.autonomous.DriveThenHotShot;
import krunch17.autonomous.SimpleDrive;
import krunch17.autonomous.SimpleDriveThenShot;
import krunch17.camera.KrunchVisionServer;
import krunch17.drivetrain.ArcadeDrive;
import krunch17.drivetrain.InvertArcadeDrive;
import krunch17.drivetrain.ShiftToHighGear;
import krunch17.drivetrain.ShiftToInverted;
import krunch17.intake.InvertIntake;
import krunch17.intake.RetractIntake;
import krunch17.intake.RollerTeleop;
import krunch17.launcher.FireLauncherAutomated;
import krunch17.launcher.LauncherOverride;
import krunch17.launcher.LightPassAutomated;
import krunch17.launcher.TestLauncher;
import krunch17.launcher.TrussShotAutomated;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CK_17 extends IterativeRobot {

    SendableChooser autoChooser;
    Command autonomousCommand, arcadeDriveCommand, initialShiftCommand,
            testLauncherCommand, rollerControlCommand, initialRetractCommand,
            fireAutomatedCommand, trussAutomatedCommand, lightPassAutomated,
            launcherOverride;
    
    KrunchVisionServer server = KrunchVisionServer.getInstance();
    public final int listenPort = 1180;

    public void robotInit() {
        // Initialize all subsystems
        RobotMap.init();
        CommandBase.init();
        
        // Setup webcam server
        server.setPort(listenPort);
        server.start();
        
        // Init autonomous modes (And send to dashboard selector)
        autoChooser = new SendableChooser();
        autoChooser.addObject("Simple Drive", new SimpleDrive());
        autoChooser.addObject("Simple Drive Then Shot", new SimpleDriveThenShot());
        autoChooser.addDefault("Drive Then Hot Shot", new DriveThenHotShot());
        autoChooser.addObject("Do Nothing", new DoNothing());
        SmartDashboard.putData("Choose an autonomous mode", autoChooser);
        
        // instantiate commands
        arcadeDriveCommand = new ArcadeDrive();
        initialShiftCommand = new ShiftToHighGear();
        rollerControlCommand = new RollerTeleop();
        testLauncherCommand = new TestLauncher();
        initialRetractCommand = new RetractIntake();
        fireAutomatedCommand = new FireLauncherAutomated();
        trussAutomatedCommand = new TrussShotAutomated();
        lightPassAutomated = new LightPassAutomated();
        launcherOverride = new LauncherOverride(fireAutomatedCommand, 
                trussAutomatedCommand, lightPassAutomated);
        
        
        // Map commands to buttons
        CommandBase.oi.shiftButton.whenPressed(new ShiftToInverted());
        CommandBase.oi.invertArcadeDriveButton.whenPressed(new InvertArcadeDrive());
        
        CommandBase.oi.toggleIntakeExtensionButton.whenPressed(new InvertIntake());
        
        CommandBase.oi.fireButton.whenPressed(fireAutomatedCommand);
        CommandBase.oi.trussShotButton.whenPressed(trussAutomatedCommand);
        CommandBase.oi.lightPassButton.whenPressed(lightPassAutomated);
        
        CommandBase.oi.cancelFireButton.whenPressed(launcherOverride);
        
        // Reset gyro
//        RobotMap.turnGyro.reset(); // Takes like 5 seconds
        
//        SmartDashboard.putNumber("RAISE_POWER", CommandBase.launcher.HIGH_GOAL_RAISE_POWER);
        
        System.out.println("--------------------------------------");
        System.out.println("  robotInit() COMPLETE ");
        System.out.println("--------------------------------------");
    }

    public void autonomousInit() {
        // Reset hot goal counts
        server.reset();
        server.startSamplingCounts();
        
        RobotMap.compressor.start(); // Start compressor
        initialShiftCommand.start();
        autonomousCommand = (Command)autoChooser.getSelected();
        autonomousCommand.start(); // schedule the autonomous command
        initialRetractCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        System.out.println("SERVER COUNT: " + server.getCount());
//        SmartDashboard.putNumber("ENC", CommandBase.drive.getAvgRevs());
    }
    
    public void teleopInit() {
        // Try catch just in case auton isn't set yet
        try {
            autonomousCommand.cancel(); // Make sure auton is finished
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
        RobotMap.compressor.start(); // Start compressor
        initialShiftCommand.start();
        arcadeDriveCommand.start(); // Start teleop arcade drive
        rollerControlCommand.start();
        initialRetractCommand.start();
        
//        CommandBase.launcher.HIGH_GOAL_RAISE_POWER = (float) SmartDashboard.getNumber("RAISE_POWER");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        SmartDashboard.putNumber("ENC", CommandBase.drive.getAvgRevs());
    }

    
    public void disabledInit() {
        // Stop counting
        server.stopSamplingCounts();
        
        Scheduler.getInstance().removeAll(); // Stop all commands
        RobotMap.compressor.stop(); // Stop compressor
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
