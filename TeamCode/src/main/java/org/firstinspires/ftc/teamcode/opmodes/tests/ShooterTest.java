package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@TeleOp(name = "Shooter test", group = "Tests")
public class ShooterTest extends CommandOpMode {
    private Shooter shooter;
    private Intake intake;
    private GamepadEx firstDriver;
    private JoinedTelemetry joinedTelemetry;
    private ShootCommand shootCommand;

    @Override
    public void initialize() {
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        joinedTelemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);

        //shoot command
        shootCommand = new ShootCommand(intake, shooter);
        firstDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(shootCommand);

        //hood testing
        firstDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> shooter.hoodUp());
        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> shooter.hoodDown());

        //shooter testing
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> shooter.stopShooter());
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> shooter.shooterDown());
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> shooter.shooterUp());


    }



    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        //Telemetry here
        shooter.telemetry();
        joinedTelemetry.update();
    }
}