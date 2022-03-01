package frc.robot.controller;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;


public class MyLogitechController extends MyBaseJoystick {

    private final GenericHID gamepadController;
    private final boolean use2Sticks;

    public MyLogitechController(GenericHID gamepadController) {
        this(gamepadController, true);
    }

    public MyLogitechController(GenericHID gamepadController, MyJoystick oldController) {
        this(gamepadController);
        setupButtons(oldController);
    }

    public MyLogitechController(GenericHID gamepadController, boolean use2Sticks) {
        this.gamepadController = gamepadController;
        this.use2Sticks = use2Sticks;
    }

    public MyLogitechController(GenericHID gamepadController, MyJoystick oldController, boolean use2Sticks) {
        this(gamepadController, use2Sticks);
        setupButtons(oldController);
    }

    @Override
    public double getLeftX() {
        return gamepadController.getRawAxis(0);
    }

    @Override
    public double getLeftY() {
        return gamepadController.getRawAxis(1);
    }

    @Override
    public double getRightX() {
        if (use2Sticks) {
            return gamepadController.getRawAxis(4);
        } else {
            return gamepadController.getRawAxis(0);
        }
    }

    @Override
    public double getRightY() {
        if (use2Sticks) {
            return gamepadController.getRawAxis(5);
        } else {
            return gamepadController.getRawAxis(1);
        }
    }

    @Override
    public void setupIntakeCargo(Command intakeCargoCommand) {
        this.intakeCargoCommand = intakeCargoCommand;
        new JoystickButton(gamepadController, 5).whileHeld(intakeCargoCommand);
    }

    @Override
    public void setupSpitOutCargo(Command spitOutCargoCommand) {
        this.spitOutCargoCommand = spitOutCargoCommand;
        new JoystickButton(gamepadController, 6).whileHeld(spitOutCargoCommand);
    }

    @Override
    public void setupArmUp(Command armUpCommand) {
        this.armUpCommand = armUpCommand;
        new JoystickButton(gamepadController, 2).whenPressed(armUpCommand);
    }

    @Override
    public void setupArmDown(Command armDownCommand) {
        this.armDownCommand = armDownCommand;
        new JoystickButton(gamepadController, 1).whenPressed(armDownCommand);
    }

}
