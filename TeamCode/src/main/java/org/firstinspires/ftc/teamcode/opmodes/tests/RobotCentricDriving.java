package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;

@TeleOp(group = "Tests", name="Simple TeleOp Driving")
public class RobotCentricDriving extends LinearOpMode {

    @Override
    public void runOpMode() {

        Chassis chassis;
        chassis = new Chassis(hardwareMap, telemetry);

        GamepadEx firstDriver = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {

            // Driving the mecanum base takes 3 joystick parameters: leftX, leftY, rightX.
            // These are related to the left stick x value, left stick y value, and
            // right stick x value respectively. These values are passed in to represent the
            // strafing speed, the forward speed, and the turning speed of the robot frame
            // respectively from [-1, 1].

            // optional fourth parameter for squared inputs
            chassis.robotCentricDriving(
                    firstDriver.getLeftX(),
                    firstDriver.getLeftY(),
                    firstDriver.getRightX()
            );

        }
    }

}
