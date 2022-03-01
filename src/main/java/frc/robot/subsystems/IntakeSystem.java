package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.*;

public class IntakeSystem extends SubsystemBase {

    private final CANSparkMax intakeMotor;

    public IntakeSystem() {
        intakeMotor = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushed);
        intakeMotor.restoreFactoryDefaults();
        intakeMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        intakeMotor.burnFlash();
    }

    public void cargoIn() {
        intakeMotor.set(.8);
    }

    public void cargoOut() {
        intakeMotor.set(-.8);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }

}
