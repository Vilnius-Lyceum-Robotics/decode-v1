package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter extends SubsystemBase implements ShooterConfiguration {
    double upperPercentage;
    double lowerPercentage;
    private MotorEx upper, lower;
    private Telemetry telemetry;

    public Shooter (HardwareMap hardwareMap, Telemetry telemetry) {

        this.upperPercentage = 0;
        this.lowerPercentage = 0;

        upper = new MotorEx(hardwareMap, SHOOTER_UPPER, Motor.GoBILDA.BARE);
        lower = new MotorEx(hardwareMap, SHOOTER_LOWER, Motor.GoBILDA.BARE);

        lower.setInverted(true);
        upper.setInverted(true);

        upper.setRunMode(Motor.RunMode.VelocityControl);
        lower.setRunMode(Motor.RunMode.VelocityControl);

        this.telemetry = telemetry;
    }

    public void increaseUpperVelocity(double increment){
        upperPercentage = Range.clip(upperPercentage+increment, 0, 1);
        upper.setVelocity(upper.getMaxRPM() * upperPercentage);

    }

    public void increaseLowerVelocity(double increment){
        lowerPercentage = Range.clip(lowerPercentage+increment, 0, 1);
        lower.setVelocity(lower.getMaxRPM() * lowerPercentage);
    }
    public void shoot(double force){
        shoot(force, force);
    }
    public void shoot(double forceLower, double forceUpper){
        increaseLowerVelocity(forceLower);
        increaseUpperVelocity(forceUpper);
    }
    public void stop(){
        upper.stopMotor();
        lower.stopMotor();
        lowerPercentage = 0;
        upperPercentage = 0;
    }

    // SIMPLE LAUNCH TO DISTANCE
    /*
    void launchToDistance(int distance)
    {
        double h = 0.000; // initial launch height
        double H = 0.000; // target height
        double r = 0.000; // rotor radius
        double k = 0.000; // slip constant (to tune)

        double velocity = 60*Math.sqrt(9.8 * d * d / (d + h - H))/(2*3.14*r*(1-k)*lower.getMaxRPM());

        shoot(velocity);
    }
     */


    public void telemetry()
    {
        telemetry.addData("Upper motor velocity: ", upper.getVelocity());
        telemetry.addData("Lower motor velocity: ", lower.getVelocity());
    }
}
