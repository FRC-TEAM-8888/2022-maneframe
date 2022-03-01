package frc.robot.controller;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;


public class MyXBoxController extends MyBaseJoystick {

    private final XboxController xboxController;
    private final boolean use2Sticks;

    public MyXBoxController(XboxController xboxController) {
        this(xboxController, true);
    }

    public MyXBoxController(XboxController xboxController, MyJoystick oldController) {
        this(xboxController);
        setupButtons(oldController);
    }

    public MyXBoxController(XboxController xboxController, boolean use2Sticks) {
        this.xboxController = xboxController;
        this.use2Sticks = use2Sticks;
    }

    public MyXBoxController(XboxController xboxController, MyJoystick oldController, boolean use2Sticks) {
        this(xboxController, use2Sticks);
        setupButtons(oldController);
    }

    @Override
    public double getLeftX() {
        return xboxController.getLeftX();
    }

    @Override
    public double getLeftY() {
        return xboxController.getLeftY();
    }

    @Override
    public double getRightX() {
        if (use2Sticks) {
            return xboxController.getRightX();
        } else {
            return xboxController.getLeftX();
        }
    }

    @Override
    public double getRightY() {
        if (use2Sticks) {
            return xboxController.getRightY();
        } else {
            return xboxController.getLeftY();
        }
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
