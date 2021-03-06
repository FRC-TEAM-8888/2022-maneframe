// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.*;
import frc.robot.controller.*;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

    // Default the controller to Logitech Gamepad w/2 stick drive
    MyJoystick myController = new MyLogitechController(new GenericHID(0), new GenericHID(1), true);
    private final DriveSystem driveSystem;
    private final IntakeSystem intakeSystem;
    private final ArmSystem armSystem;

    private boolean goForAuto;


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveSystem = new DriveSystem(myController);
        intakeSystem = new IntakeSystem();
        armSystem = new ArmSystem();
        configureButtonBindings();
        SmartDashboard.putBoolean("Go For Auto", true);
        goForAuto = SmartDashboard.getBoolean("Go For Auto", true);
    }


    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        myController.setupIntakeCargo(new IntakeCargo(intakeSystem));
        myController.setupSpitOutCargo(new SpitOutCargo(intakeSystem));
        myController.setupArmUp(new ArmUp(armSystem));
        myController.setupArmDown(new ArmDown(armSystem));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        goForAuto = SmartDashboard.getBoolean("Go For Auto", true);
        // An ExampleCommand will run in autonomous
        if (goForAuto) {
            return null;
            //return new Auto(intakeSystem, driveSystem);
        } else {
            return null;
        }
    }

    public void disabledInit() {
        // Reset the arm to up... otherwise it could start in the down direction
        armSystem.armDisabled();
        driveSystem.stop();
        intakeSystem.stop();
    }

    public void enabledInit() {
        armSystem.armEnabled();
    }

}
