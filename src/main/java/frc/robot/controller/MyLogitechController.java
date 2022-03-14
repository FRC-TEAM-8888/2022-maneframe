package frc.robot.controller;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;


public class MyLogitechController extends MyBaseJoystick {

    private final GenericHID gamepadController;
    private final GenericHID coDriverController;
    private final boolean use2Sticks;
    private final boolean coDriver;

    public MyLogitechController(GenericHID gamepadController, boolean use2Sticks) {
        this.gamepadController = gamepadController;
        this.use2Sticks = use2Sticks;
        this.coDriverController = null;
        this.coDriver = false;
    }

    public MyLogitechController(GenericHID gamepadController) {
        this(gamepadController, true);
    }

    public MyLogitechController(GenericHID gamepadController, MyJoystick oldController) {
        this(gamepadController);
        setupButtons(oldController);
    }

    public MyLogitechController(GenericHID gamepadController, MyJoystick oldController, boolean use2Sticks) {
        this(gamepadController, use2Sticks);
        setupButtons(oldController);
    }

    public MyLogitechController(GenericHID gamepadController, GenericHID coDriverController, boolean use2Sticks) {
        this.gamepadController = gamepadController;
        this.use2Sticks = use2Sticks;
        this.coDriverController = coDriverController;
        this.coDriver = true;
    }

    public MyLogitechController(GenericHID gamepadController, GenericHID coDriverController) {
        this(gamepadController, coDriverController, true);
    }

    public MyLogitechController(GenericHID gamepadController, GenericHID coDriverController, MyJoystick oldController) {
        this(gamepadController, coDriverController);
        setupButtons(oldController);
    }

    public MyLogitechController(GenericHID gamepadController, GenericHID coDriverController, MyJoystick oldController, boolean use2Sticks) {
        this(gamepadController, coDriverController, use2Sticks);
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
    public boolean getAllowTurnInPlace() {
        return gamepadController.getRawButton(3);
    }

    @Override
    public void setupIntakeCargo(Command intakeCargoCommand) {
        this.intakeCargoCommand = intakeCargoCommand;
        if (coDriver) {
            new JoystickButton(coDriverController, 6).whileHeld(intakeCargoCommand);
        } else {
            new JoystickButton(gamepadController, 6).whileHeld(intakeCargoCommand);
        }
    }

    @Override
    public void setupSpitOutCargo(Command spitOutCargoCommand) {
        this.spitOutCargoCommand = spitOutCargoCommand;
        if (coDriver) {
            new JoystickButton(coDriverController, 5).whileHeld(spitOutCargoCommand);
        } else {
            new JoystickButton(gamepadController, 5).whileHeld(spitOutCargoCommand);
        }
    }

    @Override
    public void setupArmUp(Command armUpCommand) {
        this.armUpCommand = armUpCommand;
        if (coDriver) {
            new JoystickButton(coDriverController, 2).whenPressed(armUpCommand);
        } else {
            new JoystickButton(gamepadController, 2).whenPressed(armUpCommand);
        }
    }

    @Override
    public void setupArmDown(Command armDownCommand) {
        this.armDownCommand = armDownCommand;
        if (coDriver) {
            new JoystickButton(coDriverController, 1).whenPressed(armDownCommand);
        } else {
            new JoystickButton(gamepadController, 1).whenPressed(armDownCommand);
        }
    }

}
