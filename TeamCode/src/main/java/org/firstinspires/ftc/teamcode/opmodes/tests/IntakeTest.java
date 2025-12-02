package org.firstinspires.ftc.teamcode.opmodes.tests;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.*;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ConditionalCommand;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;

@TeleOp(group = "Tests", name = "Intake test")
public class IntakeTest extends CommandOpMode {
    private Intake intake;
    @Override
    public void initialize() {
        super.reset();

        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        intake = new Intake(hardwareMap, telemetry);
        GamepadEx firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> intake.setIntake(true))
                .whenReleased(() -> intake.setIntake(false));

        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> intake.setLift(LIFT_UP_POS))
                .whenReleased(() -> intake.setLift(LIFT_DOWN_POS));
        firstDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new ConditionalCommand(
                        new InstantCommand(() -> intake.stopTransfer()),
                        new InstantCommand(() -> intake.startTransfer()),
                        () -> intake.isTransferOn()
                ));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        intake.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
