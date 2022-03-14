package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class Auto extends SequentialCommandGroup {

    public Auto(IntakeSystem intakeSystem, DriveSystem driveSystem) {
        addRequirements(intakeSystem, driveSystem);
        addCommands(
                new InstantCommand(intakeSystem::cargoOut),
                new WaitCommand(3),
                new InstantCommand(intakeSystem::stop),
                new ParallelRaceGroup(
                        new AutoDrive(driveSystem),
                        new WaitCommand(6)
                ),
                new InstantCommand(driveSystem::stop)
        );
    }

}
