package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class ArmDown extends SequentialCommandGroup {

    public ArmDown(ArmSystem armSystem) {
        addRequirements(armSystem);
        addCommands(
                new PrintCommand("ARM DOWN!!"),
                new InstantCommand(armSystem::moveArmDown, armSystem),
                new WaitCommand(1.05),
                new InstantCommand(armSystem::holdArmDown, armSystem)
        );
    }

}
