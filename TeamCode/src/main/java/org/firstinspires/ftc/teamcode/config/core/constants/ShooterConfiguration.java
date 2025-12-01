package org.firstinspires.ftc.teamcode.config.core.constants;


import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

@Configurable
public interface ShooterConfiguration {

    String SHOOTER_MOTOR = "shooter";
    public double multiplierRPM = 0.7;

    double LOW_SPIN_FORCE = 0.08;
}
