package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@TeleOp(name = "Fake TeleOP v2", group = "Tests")
public class TeleOpTestV2 extends CommandOpMode {
    private GamepadEx driver;
    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    @Override
    public void initialize() {
        super.reset();

        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);

        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);

        shootCommand = new ShootCommand(intake, shooter, 1);
        driver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(shootCommand);
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> intake.setIntakeTransfer(true));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> intake.setIntakeTransfer(false));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        chassis.drive(
                driver.getLeftX(),
                driver.getLeftY(),
                driver.getRightX()
        );

        shooter.telemetry();

        telemetry.update();
    }
}
