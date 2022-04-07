package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import io.github.oblarg.oblog.*;
import io.github.oblarg.oblog.annotations.*;

public class ArmSystem extends SubsystemBase implements Loggable {

    private final CANSparkMax armMotor;

    private static final double ARM_SPEED_UP = .4;
    private static final double ARM_SPEED_DOWN = -.4;
//    private static final double ARM_SPEED_UP = .2;
//    private static final double ARM_SPEED_DOWN = -.2;

    private static final double ARM_HOLD_UP = .09;
    private static final double ARM_HOLD_DOWN = -.15;

    private final DigitalInput topLimitSwitch = new DigitalInput(0);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(9);

    @Log.ToString
    private State armState;

    public ArmSystem() {
        System.out.println("ArmSystem");
        armMotor = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
        armMotor.enableVoltageCompensation(8);
    }

    public void moveArmUp() {
        armState = State.MOVING_UP;
        armMotor.setVoltage(12 * ARM_SPEED_UP);
    }

    public void moveArmDown() {
        armState = State.MOVING_DOWN;
        armMotor.setVoltage(12 * ARM_SPEED_DOWN);
    }

    public void holdArmUp() {
        armState = State.ARM_UP;
        armMotor.setVoltage(12 * ARM_HOLD_UP);
    }

    public void holdArmDown() {
        armState = State.ARM_DOWN;
        armMotor.setVoltage(12 * ARM_HOLD_DOWN);
    }

    public void stop() {
        armMotor.stopMotor();
    }

    public boolean armIsUp() {
        return armState.equals(State.ARM_UP);
    }

    public boolean armIsDown() {
        return armState.equals(State.ARM_DOWN);
    }

    public void armDisabled() {
        System.out.println("armDisabled!!");
        armState = State.ARM_UP;
        armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        stop();
    }

    public void armEnabled() {
        System.out.println("armEnabled!!");
        holdArmUp();
        armMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        super.periodic();

        boolean topLimitSwitchTripped = topLimitSwitch.get();
        boolean bottomLimitSwitchTripped = bottomLimitSwitch.get();
        SmartDashboard.putBoolean("Top Limit Switch", topLimitSwitchTripped);
        SmartDashboard.putBoolean("Bottom Limit Switch", bottomLimitSwitchTripped);

        // Don't bother checking if limit switches match the active state
        if (!(topLimitSwitchTripped && armState.equals(State.ARM_UP)) && !(bottomLimitSwitchTripped && armState.equals(State.ARM_DOWN))) {
            // Check to see if limit switches are triggered, and if they are in wrong state for the switch
            if (topLimitSwitchTripped && (armState.equals(State.MOVING_UP) || armState.equals(State.ARM_DOWN))) {
                holdArmUp();
                System.out.println("WARNING: Top Limit hit!!");
            } else if (bottomLimitSwitchTripped && (armState.equals(State.MOVING_DOWN) || armState.equals(State.ARM_UP))) {
                holdArmDown();
                System.out.println("WARNING: Bottom Limit hit!!");
            }
        }

    }

    public enum State {
        ARM_UP,
        ARM_DOWN,
        MOVING_UP,
        MOVING_DOWN
    }
}
