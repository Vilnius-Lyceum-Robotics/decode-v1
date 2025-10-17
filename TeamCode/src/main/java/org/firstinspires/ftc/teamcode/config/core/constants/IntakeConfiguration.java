package org.firstinspires.ftc.teamcode.config.core.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public interface IntakeConfiguration {
    String INTAKE_MOTOR = "intake";
    String CONVEYOR_BELT_MOTOR = "transfer";
    String LIFT_SERVO = "booster";
    double INTAKE_SPEED = 0.5;
    double CONVEYOR_SPEED = 0.8;
    double LIFT_MIN = 0.36;
    double LIFT_MAX = 0.42;
    double LIFT_DOWN_POS = 0.0;
//    double LIFT_HOLD_POS = 0.4;
    double LIFT_UP_POS = 0.8;
}
