package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration;

public class Chassis extends SubsystemBase implements ChassisConfiguration {
    private Motor rightFront, leftFront, rightRear, leftRear;
    private Telemetry telemetry;
    private final MecanumDrive drive;
    //private GoBildaPinpointDriver pinpoint;
    public Chassis (HardwareMap hardwareMap, Telemetry telemetry) {
        rightFront = new MotorEx(hardwareMap, RIGHT_FRONT_MOTOR, Motor.GoBILDA.RPM_435);
        leftFront = new MotorEx(hardwareMap, LEFT_FRONT_MOTOR, Motor.GoBILDA.RPM_435);
        rightRear = new MotorEx(hardwareMap, RIGHT_REAR_MOTOR, Motor.GoBILDA.RPM_435);
        leftRear = new MotorEx(hardwareMap, LEFT_REAR_MOTOR, Motor.GoBILDA.RPM_435);

        this.telemetry = telemetry;

        rightFront.setInverted(true);
        leftFront.setInverted(true);
        rightRear.setInverted(true);
        leftRear.setInverted(true);

//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
//        pinpoint.resetPosAndIMU();

        drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);

    }

    public void robotCentricDriving(double x, double y, double r) {
        drive.driveRobotCentric(x, y, r, false);
    }
}
