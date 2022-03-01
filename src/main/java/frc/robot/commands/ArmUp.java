package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class ArmUp extends SequentialCommandGroup {

    public ArmUp(ArmSystem armSystem) {
        addRequirements(armSystem);
        addCommands(
                new InstantCommand(armSystem::moveArmUp, armSystem),
                new WaitCommand(1.5),
                new InstantCommand(armSystem::holdArmUp)
        );
    }

}
