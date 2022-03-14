package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.controller.*;
import frc.robot.subsystems.*;


public class AutoDrive extends CommandBase {

    private final DriveSystem driveSystem;

    public AutoDrive(DriveSystem driveSystem) {
        addRequirements(driveSystem);
        this.driveSystem = driveSystem;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveSystem.tankDrive(.3, .3, false);
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
