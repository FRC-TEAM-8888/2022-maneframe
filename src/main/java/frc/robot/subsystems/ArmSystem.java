package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.*;
import io.github.oblarg.oblog.*;
import io.github.oblarg.oblog.annotations.*;

public class ArmSystem extends SubsystemBase implements Loggable {

    private final CANSparkMax armMotor;
    private final SparkMaxPIDController pidController;
    private final RelativeEncoder encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

//    private static final double ARM_SPEED_UP = .4;
//    private static final double ARM_SPEED_DOWN = -.4;
//    private static final double ARM_SPEED_UP = .2;
//    private static final double ARM_SPEED_DOWN = -.2;

    private static final double ARM_HOLD_UP = .09;
    private static final double ARM_HOLD_DOWN = -.15;

    private final DigitalInput topLimitSwitch = new DigitalInput(0);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(9);

    @Log.ToString
    private State armState;

    public ArmSystem() {
        armMotor = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
        armMotor.enableVoltageCompensation(8);

        pidController = armMotor.getPIDController();

        // Encoder object created to display position values
        encoder = armMotor.getEncoder();
        encoder.setPosition(0);

        // PID coefficients
        kP = 0.5;
        kI = 0;
        kD = 0;
        kIz = 0;
        kFF = 0;
        kMaxOutput = .5;
        kMinOutput = -.5;

        // set PID coefficients
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);

        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
        SmartDashboard.putNumber("Set Rotations", 0);
    }

    public void moveArmUp() {
        armState = State.MOVING_UP;
        SmartDashboard.putNumber("Set Rotations", -2);
    }

    public void moveArmDown() {
        armState = State.MOVING_DOWN;
        SmartDashboard.putNumber("Set Rotations", -31);
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

    public boolean isAtRotation() {
        double rotations = SmartDashboard.getNumber("Set Rotations", 0);
        return (encoder.getPosition() > (rotations - .1)) && (encoder.getPosition() < (rotations + .1));
    }

    public boolean armIsUp() {
        return armState.equals(State.ARM_UP);
    }

    public boolean armIsDown() {
        return armState.equals(State.ARM_DOWN);
    }

    public void armDisabled() {
        System.out.println("armDisabled!!");
        armState = State.UNKNOWN;
        armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        stop();
    }

    public void armEnabled() {
        System.out.println("armEnabled!!");
        armState = State.UNKNOWN;
        armMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        armMotor.setVoltage(12 * ARM_HOLD_UP);
    }

    public boolean isTopSwitchTriggered() {
        return topLimitSwitch.get();
    }

    public boolean isBottomSwitchTriggered() {
        return bottomLimitSwitch.get();
    }

    @Override
    public void periodic() {
        super.periodic();

        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
        double rotations = SmartDashboard.getNumber("Set Rotations", 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { pidController.setP(p); kP = p; }
        if((i != kI)) { pidController.setI(i); kI = i; }
        if((d != kD)) { pidController.setD(d); kD = d; }
        if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
        if((ff != kFF)) { pidController.setFF(ff); kFF = ff; }
        if((max != kMaxOutput) || (min != kMinOutput)) {
            pidController.setOutputRange(min, max);
            kMinOutput = min; kMaxOutput = max;
        }


        boolean topLimitSwitchTripped = isTopSwitchTriggered();
        boolean bottomLimitSwitchTripped = isBottomSwitchTriggered();
        if (topLimitSwitchTripped && (!armIsUp() && !armState.equals(State.MOVING_DOWN))) {
            // if the top limit switch is tripped, and it's not already up or moving down
            holdArmUp();
            encoder.setPosition(0);
        } else if (bottomLimitSwitchTripped && (!armIsDown() && !armState.equals(State.MOVING_UP))) {
            // if the bottom limit switch is tripped, and it's not already down or moving up
            holdArmDown();
        } else if (armState.equals(State.MOVING_UP) || armState.equals(State.MOVING_DOWN)) {
            // if arm is in process of moving up or down, then continue moving towards set rotations
            pidController.setReference(rotations, CANSparkMax.ControlType.kPosition);
        }

        SmartDashboard.putNumber("ARM Position", encoder.getPosition());
        SmartDashboard.putString("ARM State", armState.toString());
        SmartDashboard.putBoolean("Top Limit Switch", topLimitSwitchTripped);
        SmartDashboard.putBoolean("Bottom Limit Switch", bottomLimitSwitchTripped);

    }

    public enum State {
        ARM_UP,
        ARM_DOWN,
        MOVING_UP,
        MOVING_DOWN,
        UNKNOWN
    }
}
