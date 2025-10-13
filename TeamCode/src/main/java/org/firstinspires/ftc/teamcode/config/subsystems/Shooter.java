package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter implements ShooterConfiguration {
    double upperPercentage;
    double lowerPercentage;
    private MotorEx upper, lower;
    //private Telemetry telemetry;

    public Shooter (HardwareMap hardwareMap, Telemetry telemetry) {

        this.upperPercentage = 0;
        this.lowerPercentage = 0;

        upper = new MotorEx(hardwareMap, SHOOTER_UPPER, Motor.GoBILDA.RPM_435);
        lower = new MotorEx(hardwareMap, SHOOTER_LOWER, Motor.GoBILDA.RPM_435);

        upper.setRunMode(Motor.RunMode.VelocityControl);
        lower.setRunMode(Motor.RunMode.VelocityControl);

        //this.telemetry = telemetry;
    }

    public void increaseUpperVelocity(double increment){

        upperPercentage += increment;
        upperPercentage = Math.max(0, Math.min(upperPercentage, 100));
        upper.setVelocity(upper.getMaxRPM() * (upperPercentage/ 100.0));

    }

    public void increaseLowerVelocity(double increment){

        lowerPercentage += increment;
        lowerPercentage = Math.max(0, Math.min(lowerPercentage, 100));
        lower.setVelocity(upper.getMaxRPM() * (lowerPercentage/ 100.0));
    }
    public void stop(){
        upper.stopMotor();
        lower.stopMotor();
        lowerPercentage = 0;
        upperPercentage = 0;
    }

    /*
    public void telemetry()
    {
        telemetry.addData("Upper motor velocity: ", upper.getVelocity());
        telemetry.addData("Lower motor velocity: ", lower.getVelocity());
    }*/
}
