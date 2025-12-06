package org.firstinspires.ftc.teamcode.config.core.constants;


import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

@Configurable
public interface ShooterConfiguration {

    String SHOOTER_MOTOR = "shooter";
    String LIFT_SERVO = "lift";
    String SHOOTER_HOOD = "hood";
    public double multiplierRPM = 0.6;

    double LOW_SPIN_FORCE = 0.08;
    double LIFT_MIN = 0.36;
    double LIFT_MAX = 0.41;
    double LIFT_DOWN_POS = 0.0;
//    double LIFT_HOLD_POS = 0.4;
    double LIFT_UP_POS = 0.8;
}
