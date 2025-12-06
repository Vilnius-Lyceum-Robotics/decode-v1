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

        shootCommand = new ShootCommand(intake, shooter);
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> intake.setIntakeTransfer(1));
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> intake.setIntakeTransfer(0));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> intake.setIntakeTransfer(-1));
        //SHOOTER
        driver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(() -> shooter.stopShooter());
        driver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> shooter.shooterPreset(0));
        driver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> shooter.shooterPreset(1));
        driver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(() -> shooter.shooterPreset(2));


    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        chassis.drive(
                driver.getLeftY(),
                -driver.getLeftX(),
                -driver.getRightX()
        );

        telemetry.addData("Trigger: ", driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
        if(driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5){
            telemetry.addLine("Scheduled command");
            shootCommand.schedule();
        }

        shooter.telemetry();

        telemetry.update();
    }
}
