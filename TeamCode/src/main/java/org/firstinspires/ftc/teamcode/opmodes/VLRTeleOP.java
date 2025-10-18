package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.BoostCommand;
import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@TeleOp(group = "Main", name = "NOT TEST!!")

public class VLRTeleOP extends CommandOpMode {
    private GamepadEx firstDriver;
    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);

        final double rem = 0.85;
        final double add = 1d/rem;
        shootCommand = new ShootCommand(intake, shooter, 1);
        firstDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(shootCommand);
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> shootCommand.multiplyLowerForce(add));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> shootCommand.multiplyLowerForce(rem));
        firstDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                        .whenPressed(new BoostCommand(intake));
        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> {
                    if(!intake.isIntakeOn())
                    {
                        intake.setIntake(true);
                        shooter.setLowSpin(true);
                    } else {
                        intake.setIntake(false);
                        shooter.setLowSpin(false);
                    }
                });
        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> shooter.stop());
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        chassis.robotCentricDriving(
                firstDriver.getLeftX(),
                firstDriver.getLeftY(),
                firstDriver.getRightX()
        );

        telemetry.addData("Strength lower: ", shootCommand.getLowerForce());
        telemetry.addData("Strength upper: ", shootCommand.getUpperForce());
        telemetry.addData("Lift pos: ", intake.getMappedLift());
        telemetry.addData("Lift max: ", shootCommand.liftHoldPos);
        telemetry.addData("Intake speed: ", intake.getIntakeSpeed());

        telemetry.update();
    }
}
