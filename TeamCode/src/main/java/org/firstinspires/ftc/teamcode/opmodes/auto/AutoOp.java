package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.LynxConstants;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;
import static org.firstinspires.ftc.teamcode.config.pedroPathing.Points.*;

@Autonomous
public class AutoOp extends LinearOpMode {
    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    private Follower f;
    @Override
    public void runOpMode() {
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        f = Constants.createFollower(hardwareMap);
        f.setStartingPose(START_POS);

        waitForStart();
        if (opModeIsActive()) {

        }
    }
}
