package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.*;

public abstract class MyBaseJoystick implements MyJoystick {

    Command intakeCargoCommand = null;
    Command spitOutCargoCommand = null;
    Command armUpCommand = null;
    Command armDownCommand = null;

    @Override
    public Command getIntakeCargoCommand() {
        return intakeCargoCommand;
    }

    @Override
    public Command getSpitOutCargoCommand() {
        return spitOutCargoCommand;
    }

    @Override
    public Command getArmUpCommand() {
        return armUpCommand;
    }

    @Override
    public Command getArmDownCommand() {
        return armDownCommand;
    }

    void setupButtons(MyJoystick oldController) {
        if (oldController != null) {
            if (oldController.getIntakeCargoCommand() != null) {
                setupIntakeCargo(oldController.getIntakeCargoCommand());
            }
            if (oldController.getSpitOutCargoCommand() != null) {
                setupSpitOutCargo(oldController.getSpitOutCargoCommand());
            }
            if (oldController.getArmUpCommand() != null) {
                setupArmUp(oldController.getArmUpCommand());
            }
            if (oldController.getArmDownCommand() != null) {
                setupArmDown(oldController.getArmDownCommand());
            }
        }
    }

}
