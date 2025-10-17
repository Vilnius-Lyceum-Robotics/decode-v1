package org.firstinspires.ftc.teamcode.opmodes.tests;

import static org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration.LEFT_FRONT_MOTOR;
import static org.firstinspires.ftc.teamcode.config.core.constants.ChassisConfiguration.RIGHT_FRONT_MOTOR;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;

@TeleOp
@Config
public class oneMotor extends LinearOpMode {

    public static String motorName = LEFT_FRONT_MOTOR;
    @Override
    public void runOpMode() throws InterruptedException {

        MotorEx motor = new MotorEx(hardwareMap, motorName, Motor.GoBILDA.BARE);

        GamepadEx firstDriver = new GamepadEx(gamepad1);

        waitForStart();

        motor.set(1);

        while(!isStopRequested()) {}

    }
}
