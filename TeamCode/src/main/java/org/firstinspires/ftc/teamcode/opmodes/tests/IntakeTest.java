package org.firstinspires.ftc.teamcode.opmodes.tests;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.*;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;

@TeleOp(group = "Tests", name="Intake test")
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
                .whenPressed(() -> intake.setLiftRel(0.01));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> intake.setLiftRel(-0.01));
        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> intake.setLift(LIFT_UP_POS))
                .whenReleased(() -> intake.setLift(LIFT_DOWN_POS));
        firstDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(() -> intake.setLift(LIFT_HOLD_POS));

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> intake.setConveyorSpeedRel(-0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> intake.setConveyorSpeedRel(0.05));

        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> intake.setIntakeSpeedRel(-0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> intake.setIntakeSpeedRel(0.05));
    }
}
