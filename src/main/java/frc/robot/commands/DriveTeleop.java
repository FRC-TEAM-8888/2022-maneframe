package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.controller.*;
import frc.robot.subsystems.*;


public class DriveTeleop extends CommandBase {

    private final DriveSystem driveSystem;
    private final MyJoystick driveController;
    private final boolean isTankDrive;

    public DriveTeleop(DriveSystem driveSystem, MyJoystick driveController, boolean isTankDrive) {
        addRequirements(driveSystem);
        this.driveSystem = driveSystem;
        this.driveController = driveController;
        this.isTankDrive = isTankDrive;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (isTankDrive) {
            driveSystem.tankDrive(driveController.getLeftY(), driveController.getRightY());
        } else {
            driveSystem.arcadeDrive(driveController.getLeftY(), driveController.getRightX());
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
