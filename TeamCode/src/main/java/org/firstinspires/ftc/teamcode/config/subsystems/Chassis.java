package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.geometry.Vector2d;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration;
import org.firstinspires.ftc.teamcode.config.subsystems.helpers.AsymmetricLowPassFilter;

public class Chassis extends SubsystemBase implements ChassisConfiguration {
    private final MecanumDrive drive;
    public static double motorPower = 0.5;
    public static double acceleration_a = 0.95;
    public static double deceleration_a = 0.6;

    public AsymmetricLowPassFilter x_filter;
    public AsymmetricLowPassFilter y_filter;
    public static double strafeMultiplier = 0.1;

    //private GoBildaPinpointDriver pinpoint;
    public Chassis (HardwareMap hardwareMap, Telemetry telemetry) {
        Motor rightFront = new MotorEx(hardwareMap, RIGHT_FRONT_MOTOR, Motor.GoBILDA.BARE);
        Motor leftFront = new MotorEx(hardwareMap, LEFT_FRONT_MOTOR, Motor.GoBILDA.BARE);
        Motor rightRear = new MotorEx(hardwareMap, RIGHT_REAR_MOTOR, Motor.GoBILDA.BARE);
        Motor leftRear = new MotorEx(hardwareMap, LEFT_REAR_MOTOR, Motor.GoBILDA.BARE);

        leftFront.setInverted(true);
        leftRear.setInverted(true);

        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
//        pinpoint.resetPosAndIMU();

        x_filter = new AsymmetricLowPassFilter(acceleration_a, deceleration_a);
        y_filter = new AsymmetricLowPassFilter(acceleration_a, deceleration_a);

        drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);
        drive.setRightSideInverted(false);
    }

    public void drive(double x, double y, double r) {
        Vector2d vector = new Vector2d(x_filter.estimatePower(x), y_filter.estimatePower(y) * strafeMultiplier); // USE FOR AUTO

        double frontLeftSpeed = x - y - (2 * FRONT_TRACK_RADIUS * r);
        double frontRightSpeed = x + y + (2 * FRONT_TRACK_RADIUS * r);
        double rearLeftSpeed = x + y - (2 * REAR_TRACK_RADIUS * r);
        double rearRightSpeed = x - y + (2 * REAR_TRACK_RADIUS * r);

        this.setMotors(frontLeftSpeed, frontRightSpeed, rearRightSpeed, rearLeftSpeed);
    }
    public void setMotors(double flSpeed, double frSpeed, double rrSpeed, double rlSpeed) {
        drive.driveWithMotorPowers(flSpeed * motorPower, frSpeed * motorPower,
                rlSpeed * motorPower, rrSpeed * motorPower);
    }
}
