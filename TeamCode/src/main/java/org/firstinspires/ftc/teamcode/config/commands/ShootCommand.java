package org.firstinspires.ftc.teamcode.config.commands;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_DOWN_POS;
import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.LIFT_UP_POS;

import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

public class ShootCommand extends SequentialCommandGroup {
    private double upperForce;
    public void setUpperForce(double newForce){
        upperForce = Range.clip(newForce, 0, 1);
    }
    public void addUpperForce(double change){
        setUpperForce(upperForce+change);
    }
    public double getUpperForce(){
        return upperForce;
    }
    public void multiplyUpperForce(double change){
        setUpperForce(upperForce*change);
    }
    private double lowerForce;
    public void setLowerForce(double newForce){
        lowerForce = Range.clip(newForce, 0, 1);
    }
    public void addLowerForce(double change){
        setLowerForce(lowerForce+change);
    }
    public void multiplyLowerForce(double change){
        setLowerForce(lowerForce*change);
    }
    public double getLowerForce(){
        return lowerForce;
    }
    public void setForce(double newForce){
        setLowerForce(newForce);
        setUpperForce(newForce);
    }
    public void addForce(double change){
        addLowerForce(change);
        addUpperForce(change);
    }
    public void multiplyForce(double change){
        multiplyLowerForce(change);
        multiplyUpperForce(change);
    }
    public void setVector(Vector2d vector){
        vector.times(Math.max(1, Math.max(vector.getX(), vector.getY())));
        setLowerForce(vector.getY());
        setUpperForce(vector.getX());
    }
    public ShootCommand(Intake intake, Shooter shooter, double initialForce){
        this(intake, shooter, initialForce, initialForce);
    }
    public ShootCommand(Intake intake, Shooter shooter, double initialForceLower, double initialForceUpper){
        lowerForce = initialForceLower;
        upperForce = initialForceUpper;
        addCommands(
            new InstantCommand(() -> shooter.shoot(lowerForce, upperForce)),
            new WaitCommand(2000),
            new InstantCommand(() -> intake.setLift(LIFT_UP_POS)),
            new WaitCommand(1000),
            new InstantCommand(shooter::stop),
            new WaitCommand(500),
            new InstantCommand(() -> intake.setLift(LIFT_DOWN_POS))
        );
        addRequirements(intake, shooter);
    }
}
