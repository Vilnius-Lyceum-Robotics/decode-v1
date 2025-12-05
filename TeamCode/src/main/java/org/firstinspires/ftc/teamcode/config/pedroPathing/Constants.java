package org.firstinspires.ftc.teamcode.config.pedroPathing;

import static org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration.*;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(7.4)
            .forwardZeroPowerAcceleration(-65.1175640700954)
            .lateralZeroPowerAcceleration(-43.293445885527234)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.04, 0, 0.004, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(0.45, 0, 0.02, 0.05))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.02, 0, 0.002, 0, 0));

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(0.6)
            .rightFrontMotorName(RIGHT_FRONT_MOTOR)
            .rightRearMotorName(RIGHT_REAR_MOTOR)
            .leftRearMotorName(LEFT_REAR_MOTOR)
            .leftFrontMotorName(LEFT_FRONT_MOTOR)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(75.59249373308316)
            .yVelocity(37.79624686654158);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-2.23)
            .strafePodX(6.3)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.95, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}