package org.firstinspires.ftc.teamcode.config.commands;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_DOWN_POS;
import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_UP_POS;

import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

public class ShootCommand extends SequentialCommandGroup {
    private double force;
    public void addForce(double change){
        force = Range.clip(force+change, 0, 1);
    }
    public void setForce(double newForce){
        force = Range.clip(newForce, 0, 1);
    }
    public double getForce(){
        return force;
    }
    public ShootCommand(Intake intake, Shooter shooter, double initialForce){
        force = initialForce;
        addCommands(
            new InstantCommand(() -> shooter.shoot(force)),
            new WaitCommand(2000),
            new InstantCommand(() -> intake.setLift(LIFT_UP_POS)),
            new WaitCommand(300),
            new InstantCommand(() -> intake.setLift(LIFT_DOWN_POS)),
            new WaitCommand(500),
            new InstantCommand(shooter::stop)
        );
        addRequirements(intake, shooter);
    }
}
