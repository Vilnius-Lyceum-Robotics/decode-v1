package org.firstinspires.ftc.teamcode.config.commands;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_DOWN_POS;
import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_UP_POS;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

import java.util.concurrent.atomic.AtomicBoolean;

public class Shoot extends SequentialCommandGroup {
    private static final AtomicBoolean isRunning = new AtomicBoolean();
    public Shoot(Intake intake, Shooter shooter, double force){
        if(isRunning.get()) return;
        isRunning.set(true);
        addCommands(
            new InstantCommand(() -> intake.setLift(LIFT_UP_POS)),
            new WaitCommand(500),
            new InstantCommand(() -> shooter.shoot(force)),
            new WaitCommand(500),
            new InstantCommand(() -> intake.setLift(LIFT_DOWN_POS)),
            new WaitCommand(1500),
            new InstantCommand(shooter::stop)
        );
        addRequirements(intake, shooter);
    }
    @Override
    public void end(boolean interrupted) {
        isRunning.set(false);

        super.end(interrupted);
    }
}
