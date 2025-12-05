package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;
import static org.firstinspires.ftc.teamcode.config.pedroPathing.PointsFar.*;

@Autonomous
public class AutoOp extends OpMode {
    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    private Follower f;
    @Override
    public void init() {
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        f = Constants.createFollower(hardwareMap);
        f.setStartingPose(START_POS);
    }

    @Override
    public void loop() {

    }
}
