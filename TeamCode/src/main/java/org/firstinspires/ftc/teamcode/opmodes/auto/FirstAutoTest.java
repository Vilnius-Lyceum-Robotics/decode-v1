package org.firstinspires.ftc.teamcode.opmodes.auto;

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
import static org.firstinspires.ftc.teamcode.config.pedroPathing.PointsFar.*;

import java.util.ArrayList;

@Autonomous(name = "Auto test v1", group = "Auto")
public class FirstAutoTest extends OpMode {
    private class PathHolder extends StageHandler{
        private PathChain path;
        public PathHolder(PathChain path){
            this.path = path;
        }
        @Override
        public void start() {
            f.followPath(path);
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
            command.execute();
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
                .setLinearHeadingInterpolation(pos1.getHeading(), pos2.getHeading())
                .build();
    }
    public void buildCommands(){
        ArrayList<StageHandler> stagesList = new ArrayList<>();

        stagesList.add(new PathHolder(getPath(START_POS, SHOOT_POS)));
        stagesList.add(new CommandHolder(shootCommand));
        stagesList.add(new PathHolder(getPath(SHOOT_POS, START_POS)));

        stages = new StageHandler[stagesList.size()];
        stages = stagesList.toArray(stages);
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

        telemetry.addLine("Running setup");
        telemetry.update();
    }

    public void autoUpdate() {
        telemetry.addData("Running loop with stage ", stageInd);
        if(stageInd >= stages.length || (stageInd > 0 && stages[stageInd].isBusy())) return;
        stageInd++;
        if(stageInd >= stages.length) return;
        stages[stageInd].start();
    }

    @Override
    public void loop() {
        telemetry.addLine("Running loop");

        f.update();
        autoUpdate();

        telemetry.update();
    }
}
