package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;

public class BoostCommand extends SequentialCommandGroup {
    public BoostCommand(Intake intake) {
        addCommands(
//                new InstantCommand(() -> intake.setLift(IntakeConfiguration.LIFT_UP_POS)),
                new WaitCommand(1000)
//                new InstantCommand(() -> intake.setLift(IntakeConfiguration.LIFT_DOWN_POS))
        );
        addRequirements(intake);
    }
}
