package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;


public class IntakeCargo extends CommandBase {

    private final IntakeSystem intakeSystem;

    public IntakeCargo(IntakeSystem intakeSystem) {
        addRequirements(intakeSystem);
        this.intakeSystem = intakeSystem;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intakeSystem.cargoIn();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intakeSystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
