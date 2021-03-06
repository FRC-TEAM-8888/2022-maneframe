package frc.robot.subsystems;

import com.google.gson.*;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.controller.*;
import frc.robot.logging.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

import static frc.robot.Constants.DriveConstants.*;


public class DriveSystem extends SubsystemBase {

    private final DifferentialDrive drive;
    private final PowerDistribution pdp = new PowerDistribution(0, PowerDistribution.ModuleType.kCTRE);

    private FileWriter driveLoggingFileWriter;
    private final Timer timer = new Timer();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();;

    // Default to Logitech Gamepad using 2 sticks
    //private final SendableChooser<Integer> driveTypeChooser;
    //private int driveType = 8;
    //private MyJoystick driveController;

    /**
     * Creates a new DriveBase.
     */
    public DriveSystem(MyJoystick myController) {
        //this.driveController = myController;

        // Setup Left Side Motors
        final CANSparkMax leftMotor1 = new CANSparkMax(kLeftMotor1Port, CANSparkMaxLowLevel.MotorType.kBrushed);
        final CANSparkMax leftMotor2 = new CANSparkMax(kLeftMotor2Port, CANSparkMaxLowLevel.MotorType.kBrushed);
        final MotorControllerGroup leftSideMotors = new MotorControllerGroup(leftMotor1, leftMotor2);

        // Setup Right Side Motors
        final CANSparkMax rightMotor1 = new CANSparkMax(Constants.DriveConstants.kRightMotor1Port, CANSparkMaxLowLevel.MotorType.kBrushed);
        rightMotor1.setInverted(true);
        final CANSparkMax rightMotor2 = new CANSparkMax(Constants.DriveConstants.kRightMotor2Port, CANSparkMaxLowLevel.MotorType.kBrushed);
        rightMotor2.setInverted(true);
        final MotorControllerGroup rightSideMotors = new MotorControllerGroup(rightMotor1, rightMotor2);

        drive = new DifferentialDrive(leftSideMotors, rightSideMotors);
        //drive.setDeadband(.01);
        setDefaultCommand(new DriveTeleop(this, myController, false));

        /*
        Drive Type:
        1 :: 2 Flight Sticks - TankDrive
        2 :: 2 Flight Sticks - ArcadeDrive
        3 :: 1 Flight Stick  - ArcadeDrive
        4 :: 1 XBox Ctrl - TankDrive
        5 :: 1 XBox Ctrl - ArcadeDrive (2 sticks)
        6 :: 1 XBox Ctrl - ArcadeDrive (left stick)
        7 :: 1 Logitech Ctrl  - TankDrive
        8 :: 1 Logitech Ctrl  - ArcadeDrive (2 sticks)
        9 :: 1 Logitech Ctrl  - ArcadeDrive (left stick)
        */
        /*
        driveTypeChooser = new SendableChooser<>();
        driveTypeChooser.addOption("2 Flight Sticks - Tank", 1);
        driveTypeChooser.addOption("2 Flight Sticks - Arcade", 2);
        driveTypeChooser.addOption("Flight Stick - Arcade", 3);
        driveTypeChooser.addOption("Xbox - Tank", 4);
        driveTypeChooser.addOption("Xbox - Arcade - 2 sticks", 5);
        driveTypeChooser.addOption("Xbox - Arcade - 1 stick", 6);
        driveTypeChooser.addOption("Logitech Ctrl - Tank", 7);
        driveTypeChooser.setDefaultOption("Logitech Ctrl - Arcade - 2 sticks", 8);
        driveTypeChooser.addOption("Logitech Ctrl - Arcade - 1 stick", 9);
        SmartDashboard.putData("Drive Type", driveTypeChooser);
        setDefaultCommand(changeDriveCommand(driveType));

         */
    }

    public void arcadeDrive(double speed, double turn, boolean squaredInputs) {
        drive.arcadeDrive(speed, turn * -1, squaredInputs);
        var speeds = DifferentialDrive.arcadeDriveIK(speed, turn * -1, squaredInputs);
        logDriveData(speed,turn * -1, speeds.left, speeds.right);
    }

    public void tankDrive(double leftSide, double rightSide, boolean squaredInputs) {
        drive.tankDrive(leftSide, rightSide, squaredInputs);
    }

    public void stop() {
        drive.stopMotor();
    }


    @Override
    public void periodic() {
        /*
        SmartDashboard.putNumber("LEFT POWER 1", pdp.getCurrent(1));
        SmartDashboard.putNumber("LEFT POWER 2", pdp.getCurrent(0));
        SmartDashboard.putNumber("RIGHT POWER 1", pdp.getCurrent(3));
        SmartDashboard.putNumber("RIGHT POWER 2", pdp.getCurrent(2));

         */
        /*
        int chooserDriveType = driveTypeChooser.getSelected();
        if (driveType < 0 || chooserDriveType != driveType) {
            getDefaultCommand().cancel();
            setDefaultCommand(changeDriveCommand(chooserDriveType));
            driveType = chooserDriveType;
        }

         */
    }

    /*
    public Command changeDriveCommand(int chooserDriveType) {
        if (chooserDriveType == 1 || chooserDriveType == 2 || chooserDriveType == 3) {
            // Joysticks/Flight Sticks
            Joystick joystick = new Joystick(0);
            if (chooserDriveType == 1 || chooserDriveType == 2) {
                driveController = new MyFlightstick(joystick, new Joystick(1), driveController);
            } else {
                driveController = new MyFlightstick(joystick, driveController);
            }
        } else if (chooserDriveType == 4 || chooserDriveType == 5 || chooserDriveType == 6) {
            driveController = new MyXBoxController(new XboxController(0), driveController, chooserDriveType != 6);
        } else if (chooserDriveType == 7 || chooserDriveType == 8 || chooserDriveType == 9) {
            // IF there is a CO DRIVER... uncomment this and comment out the single controller line
            driveController = new MyLogitechController(new GenericHID(0), new GenericHID(1), driveController, chooserDriveType != 9);
            //driveController = new MyLogitechController(new GenericHID(0), driveController, chooserDriveType != 9);
        }

        if (chooserDriveType == 1 || chooserDriveType == 4 || chooserDriveType == 7) {
            return new DriveTeleop(this, driveController, true);
        } else {
            return new DriveTeleop(this, driveController, false);
        }
    }

     */

    public void logDriveData(double arcadeSpeed, double arcadeRotation, double leftSignal, double rightSignal) {
        DriveLog driveLogEntry = new DriveLog();
        driveLogEntry.setTime(timer.get());
        driveLogEntry.setVoltage(pdp.getVoltage());
        driveLogEntry.setLeftMotorCurrent1(pdp.getCurrent(1));
        driveLogEntry.setLeftMotorCurrent2(pdp.getCurrent(0));
        driveLogEntry.setRightMotorCurrent1(pdp.getCurrent(3));
        driveLogEntry.setRightMotorCurrent2(pdp.getCurrent(2));
        driveLogEntry.setArcadeSpeed(arcadeSpeed);
        driveLogEntry.setArcadeRotation(arcadeRotation);
        driveLogEntry.setLeftSignal(leftSignal);
        driveLogEntry.setRightSignal(rightSignal);

        try {
            driveLoggingFileWriter.write(gson.toJson(driveLogEntry) + ",");
        } catch (IOException ioe) {
            //System.out.println("ERROR: IO error when logging to telemetry file.");
            ioe.printStackTrace();
        } catch (Exception e) {
            //System.out.println("ERROR: Unknown error when logging to telemetry file.");
            e.printStackTrace();
        }
    }
    public void startLogging() {
        //Create file name
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd_HHmm");
        String fileName = "/U/" + dt.format(format) + "_Teleop_Telemetry_Log.txt";

        try {
            File driveLoggingFile = new File(fileName);
            if (driveLoggingFile.createNewFile()) {
                driveLoggingFileWriter = new FileWriter(driveLoggingFile);
                // Start json array
                driveLoggingFileWriter.write("[");
            } else {
                driveLoggingFileWriter = new FileWriter(driveLoggingFile, true);
            }
            SmartDashboard.putString("driveLogFileStatus", "green");
        } catch (IOException ioe) {
            System.out.println("ERROR: IO Error when opening telemetry log.");
            ioe.printStackTrace();
            SmartDashboard.putString("driveLogFileStatus", "red");
        } catch (Exception e) {
            System.out.println("ERROR: Unknown error while opening telemetry log.");
            e.printStackTrace();
            SmartDashboard.putString("driveLogFileStatus", "red");
        }
        timer.start();
    }

    public void stopLogging() {
        try {
            // End json array
            driveLoggingFileWriter.write("]");
            driveLoggingFileWriter.flush();
            driveLoggingFileWriter.close();
            SmartDashboard.putString("driveLogFileStatus", "blue");
        } catch (Exception e) {
            SmartDashboard.putString("driveLogFileStatus", "yellow");
        }
        timer.stop();
    }

}