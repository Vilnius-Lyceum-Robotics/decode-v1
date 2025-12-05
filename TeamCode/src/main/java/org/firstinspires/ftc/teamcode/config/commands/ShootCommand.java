package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

public class ShootCommand extends SequentialCommandGroup {

    public ShootCommand(Intake intake, Shooter shooter){

        addCommands(
                new InstantCommand(() -> intake.setTransfer(1, true)),
                new WaitCommand(500),
                new InstantCommand(() -> shooter.setLift(ShooterConfiguration.LIFT_UP_POS)),
                new WaitCommand(500),
                new InstantCommand(() -> intake.setTransfer(1, false)),
                new InstantCommand(() -> shooter.setLift(ShooterConfiguration.LIFT_DOWN_POS))
        );
        addRequirements(intake, shooter);
    }
}
