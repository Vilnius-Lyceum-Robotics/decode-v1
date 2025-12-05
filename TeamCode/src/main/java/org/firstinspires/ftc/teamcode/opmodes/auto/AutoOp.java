package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@Autonomous
public class AutoOp extends LinearOpMode {
    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    @Override
    public void runOpMode() {
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        waitForStart();
//        if (opModeIsActive()) {
//
//        }
    }
}
