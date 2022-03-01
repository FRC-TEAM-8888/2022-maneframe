package frc.robot.logging;


public class DriveLog {

    private double time;
    private double voltage;
    private double stickX;
    private double stickY;
    private double leftSignal;
    private double rightSignal;
    private double acceleration;
    private double heading;
    private int driveType;
    private double leftEncoder;
    private double rightEncoder;

    public DriveLog() {
    }

    public DriveLog(double time, double voltage, double stickX, double stickY, double leftSignal, double rightSignal, double acceleration, double heading, int driveType, double leftEncoder, double rightEncoder) {
        this.time = time;
        this.voltage = voltage;
        this.stickX = stickX;
        this.stickY = stickY;
        this.leftSignal = leftSignal;
        this.rightSignal = rightSignal;
        this.acceleration = acceleration;
        this.heading = heading;
        this.driveType = driveType;
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
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

    public double getStickX() {
        return stickX;
    }

    public void setStickX(double stickX) {
        this.stickX = stickX;
    }

    public double getStickY() {
        return stickY;
    }

    public void setStickY(double stickY) {
        this.stickY = stickY;
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
