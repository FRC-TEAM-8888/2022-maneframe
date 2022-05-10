package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class ArmUp extends CommandBase {

    private final ArmSystem armSystem;

    public ArmUp(ArmSystem armSystem) {
        addRequirements(armSystem);
        this.armSystem = armSystem;
        armSystem.moveArmUp();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return armSystem.isAtRotation() || armSystem.isTopSwitchTriggered();
    }

    @Override
    public void end(boolean interrupted) {
        if (!interrupted) {
            armSystem.holdArmUp();
        }
        super.end(interrupted);
    }
}
