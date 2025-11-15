package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration;

public class Chassis extends SubsystemBase implements ChassisConfiguration {
    private final MecanumDrive drive;

    //private GoBildaPinpointDriver pinpoint;
    public Chassis (HardwareMap hardwareMap, Telemetry telemetry) {
        Motor rightFront = new MotorEx(hardwareMap, RIGHT_FRONT_MOTOR, Motor.GoBILDA.BARE);
        Motor leftFront = new MotorEx(hardwareMap, LEFT_FRONT_MOTOR, Motor.GoBILDA.BARE);
        Motor rightRear = new MotorEx(hardwareMap, RIGHT_REAR_MOTOR, Motor.GoBILDA.BARE);
        Motor leftRear = new MotorEx(hardwareMap, LEFT_REAR_MOTOR, Motor.GoBILDA.BARE);

        rightFront.setInverted(true);
        leftFront.setInverted(true);
        rightRear.setInverted(true);
        leftRear.setInverted(true);

        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
//        pinpoint.resetPosAndIMU();

        drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);

    }

    public void robotCentricDriving(double x, double y, double r) {
        drive.driveRobotCentric(x, y, r, false);
    }
}
