package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;

public class IntakeTest extends CommandOpMode {
    private Intake intake;
    private GamepadEx firstDriver;
    @Override
    public void initialize() {
        super.reset();

        intake = new Intake(hardwareMap, null);
        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> intake.setIntake(true))
                .whenReleased(() -> intake.setIntake(false));
        firstDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(() -> intake.setConveyor(true))
                .whenReleased(() -> intake.setConveyor(false));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> intake.setLiftRel(0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> intake.setLiftRel(-0.05));
    }
}
