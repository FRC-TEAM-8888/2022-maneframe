package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.*;


public interface MyJoystick {

    double getLeftX();
    double getLeftY();
    double getRightX();
    double getRightY();

    void setupIntakeCargo(Command intakeCargoCommand);
    void setupSpitOutCargo(Command spitOutCargoCommand);
    void setupArmUp(Command armUpCommand);
    void setupArmDown(Command armDownCommand);
    Command getIntakeCargoCommand();
    Command getSpitOutCargoCommand();
    Command getArmUpCommand();
    Command getArmDownCommand();

}
