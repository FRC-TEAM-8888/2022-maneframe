package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class ArmDown extends CommandBase {

    private final ArmSystem armSystem;

    public ArmDown(ArmSystem armSystem) {
        addRequirements(armSystem);
        this.armSystem = armSystem;
        armSystem.moveArmDown();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return armSystem.isAtRotation() || armSystem.isBottomSwitchTriggered();
    }

    @Override
    public void end(boolean interrupted) {
        if (!interrupted) {
            armSystem.holdArmDown();
        }
        super.end(interrupted);
    }

}
