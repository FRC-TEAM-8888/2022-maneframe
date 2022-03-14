package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.*;

public class IntakeSystem extends SubsystemBase {

    private final CANSparkMax intakeMotor;

    public IntakeSystem() {
        intakeMotor = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushed);
        intakeMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        intakeMotor.enableVoltageCompensation(11);
    }

    public void cargoIn() {
        intakeMotor.setVoltage(12 * -1);
    }

    public void cargoOut() {
        intakeMotor.setVoltage(12 * 1);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }

}
