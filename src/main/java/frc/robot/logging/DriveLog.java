package frc.robot.logging;


public class DriveLog {

    private double time;
    private double voltage;
    private double leftMotorCurrent1;
    private double leftMotorCurrent2;
    private double rightMotorCurrent1;
    private double rightMotorCurrent2;
    private double arcadeSpeed;
    private double arcadeRotation;
    private double leftSignal;
    private double rightSignal;
    private double acceleration;
    private double heading;
    private int driveType;
    private double leftEncoder;
    private double rightEncoder;

    public DriveLog() {
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getLeftMotorCurrent1() {
        return leftMotorCurrent1;
    }

    public void setLeftMotorCurrent1(double leftMotorCurrent1) {
        this.leftMotorCurrent1 = leftMotorCurrent1;
    }

    public double getLeftMotorCurrent2() {
        return leftMotorCurrent2;
    }

    public void setLeftMotorCurrent2(double leftMotorCurrent2) {
        this.leftMotorCurrent2 = leftMotorCurrent2;
    }

    public double getRightMotorCurrent1() {
        return rightMotorCurrent1;
    }

    public void setRightMotorCurrent1(double rightMotorCurrent1) {
        this.rightMotorCurrent1 = rightMotorCurrent1;
    }

    public double getRightMotorCurrent2() {
        return rightMotorCurrent2;
    }

    public void setRightMotorCurrent2(double rightMotorCurrent2) {
        this.rightMotorCurrent2 = rightMotorCurrent2;
    }

    public double getArcadeSpeed() {
        return arcadeSpeed;
    }

    public void setArcadeSpeed(double arcadeSpeed) {
        this.arcadeSpeed = arcadeSpeed;
    }

    public double getArcadeRotation() {
        return arcadeRotation;
    }

    public void setArcadeRotation(double arcadeRotation) {
        this.arcadeRotation = arcadeRotation;
    }

    public double getLeftSignal() {
        return leftSignal;
    }

    public void setLeftSignal(double leftSignal) {
        this.leftSignal = leftSignal;
    }

    public double getRightSignal() {
        return rightSignal;
    }

    public void setRightSignal(double rightSignal) {
        this.rightSignal = rightSignal;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public int getDriveType() {
        return driveType;
    }

    public void setDriveType(int driveType) {
        this.driveType = driveType;
    }

    public double getLeftEncoder() {
        return leftEncoder;
    }

    public void setLeftEncoder(double leftEncoder) {
        this.leftEncoder = leftEncoder;
    }

    public double getRightEncoder() {
        return rightEncoder;
    }

    public void setRightEncoder(double rightEncoder) {
        this.rightEncoder = rightEncoder;
    }
}
