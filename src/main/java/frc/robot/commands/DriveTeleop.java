package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.controller.*;
import frc.robot.subsystems.*;


public class DriveTeleop extends CommandBase {

    private final DriveSystem driveSystem;
    private final MyJoystick driveController;
    private final boolean isTankDrive;
    private final int driveType;

    /**
     * Creates a new ArcadeDrive.
     */
    public DriveTeleop(DriveSystem driveSystem, MyJoystick driveController, int driveType) {
        addRequirements(driveSystem);
        this.driveSystem = driveSystem;
        this.driveController = driveController;
        this.driveType = driveType;
        this.isTankDrive = false;
    }

    public DriveTeleop(DriveSystem driveSystem, MyJoystick driveController, int driveType, boolean isTankDrive) {
        addRequirements(driveSystem);
        this.driveSystem = driveSystem;
        this.driveController = driveController;
        this.driveType = driveType;
        this.isTankDrive = isTankDrive;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        driveSystem.startLogging();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (isTankDrive) {
            double leftSide = driveController.getLeftY();
            double rightSide = driveController.getRightY();

            driveSystem.tankDrive(driveController.getLeftY(), driveController.getRightY());
            driveSystem.logDriveData(leftSide, rightSide, leftSide, rightSide, driveType);
        } else {
            double stickY = driveController.getLeftY();
            double stickX = driveController.getRightX();

            double leftSignal = stickX + stickY;
            double rightSignal = stickX - stickY;

            if(leftSignal > 1) leftSignal = 1;
            if(leftSignal < -1) leftSignal = -1;
            if(rightSignal > 1) rightSignal = 1;
            if(rightSignal < -1) rightSignal = -1;

            driveSystem.arcadeDrive(stickY, stickX);
            driveSystem.logDriveData(stickX, stickY, leftSignal, rightSignal, driveType);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveSystem.stopLogging();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
