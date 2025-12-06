package org.firstinspires.ftc.teamcode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.config.pedroPathing.PointsFar.SHOOT_POS2;
import static org.firstinspires.ftc.teamcode.config.pedroPathing.PointsFar.SHOOT_POS3;
import static org.firstinspires.ftc.teamcode.config.pedroPathing.PointsFar.START_POS;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Command;

import org.firstinspires.ftc.teamcode.config.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.config.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

import java.util.ArrayList;

@Autonomous(name = "Auto test v1.4", group = "Auto")
public class FirstAutoTest4 extends OpMode {
    private class PathHolder extends StageHandler{
        private PathChain path;
        public PathHolder(PathChain path){
            this.path = path;
        }
        @Override
        public void start() {
            f.followPath(path, false);
        }
        @Override
        public boolean isBusy() {
            return f.isBusy();
        }
    }
    private class CommandHolder extends StageHandler {
        private Command command;
        public CommandHolder(Command command){
            this.command = command;
        }
        @Override
        public void start() {
            command.schedule();
        }

        @Override
        public boolean isBusy() {
            return !command.isFinished();
        }
    }
    private abstract class StageHandler {
        public abstract void start();
        public abstract boolean isBusy();
    }







    private Intake intake;
    private Shooter shooter;
    private Chassis chassis;
    private ShootCommand shootCommand;
    private Follower f;

    private StageHandler[] stages;
    int stageInd = -1;
    public PathChain getPath(Pose pos1, Pose pos2){
        return f.pathBuilder()
                .addPath(new BezierLine(pos1, pos2))
//                .setLinearHeadingInterpolation(pos1.getHeading(), pos2.getHeading(), 0.8)
                .setConstantHeadingInterpolation(pos2.getHeading())
                .build();
    }
    PathChain path1;
    PathChain path2;
    public void buildCommands(){
        path1 = getPath(START_POS, SHOOT_POS3);
        path2 = getPath(SHOOT_POS3, START_POS);
    }
    @Override
    public void init() {
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        intake = new Intake(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);
        shootCommand = new ShootCommand(intake, shooter);

        f = Constants.createFollower(hardwareMap);
        f.setStartingPose(START_POS);

        buildCommands();

//        telemetry.addData("Stages found: ", stages.length);
        telemetry.update();
    }
    public void start(){
        pathState = 0;
    }

    private int pathState = -1;
    public void autoUpdate() {
        telemetry.addData("Current state: ", pathState);

        switch (pathState) {
            case 0:
                f.followPath(path1);
                pathState = 1;
                break;
            case 1:
                if(!f.isBusy()) {
                    f.followPath(path2,true);
                    pathState = 2;
                }
                break;
            case 2:
                if(!f.isBusy()) {
                    pathState = -1;
                }
                break;
        }
    }

    @Override
    public void loop() {
        telemetry.addLine("Running loop");

        f.update();
        autoUpdate();

        telemetry.update();
    }
}
