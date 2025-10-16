package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.Shoot;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@TeleOp(group = "Main", name = "NOT TEST!!")

public class VLRTeleOP extends CommandOpMode {
    private GamepadEx firstDriver;
    private GamepadEx secondDriver;
    private Intake intake;
    private Shooter shooter;
    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);
        secondDriver = new GamepadEx(gamepad2);

        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> new Shoot(intake, shooter, 1));

        firstDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> intake.setIntake(true))
                .whenReleased(() -> intake.setIntake(false));
    }
}
