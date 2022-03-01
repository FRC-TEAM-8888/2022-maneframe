package frc.robot.controller;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;


public class MyFlightstick extends MyBaseJoystick {

    private final Joystick joystick1;
    private final Joystick joystick2;

    public MyFlightstick(Joystick joystick) {
        this(joystick, joystick);
    }

    public MyFlightstick(Joystick joystick, MyJoystick oldController) {
        this(joystick);
        setupButtons(oldController);
    }

    public MyFlightstick(Joystick leftJoystick, Joystick rightJoystick) {
        this.joystick1 = leftJoystick;
        this.joystick2 = rightJoystick;
    }

    public MyFlightstick(Joystick leftJoystick, Joystick rightJoystick, MyJoystick oldController) {
        this(leftJoystick, rightJoystick);
        setupButtons(oldController);
    }

    @Override
    public double getLeftX() {
        return joystick1.getX();
    }

    @Override
    public double getLeftY() {
        return joystick1.getY();
    }

    @Override
    public double getRightX() {
        return joystick2.getX();
    }

    @Override
    public double getRightY() {
        return joystick2.getY();
    }

    @Override
    public void setupIntakeCargo(Command intakeCargoCommand) {
        this.intakeCargoCommand = intakeCargoCommand;
        // setup button for intake
    }

    @Override
    public void setupSpitOutCargo(Command spitOutCargoCommand) {
        this.spitOutCargoCommand = spitOutCargoCommand;
        // setup button for intake
    }

    @Override
    public void setupArmUp(Command armUpCommand) {
        this.armUpCommand = armUpCommand;
        // setup button for arm
    }

    @Override
    public void setupArmDown(Command armDownCommand) {
        this.armDownCommand = armDownCommand;
        // setup button for arm
    }

}
