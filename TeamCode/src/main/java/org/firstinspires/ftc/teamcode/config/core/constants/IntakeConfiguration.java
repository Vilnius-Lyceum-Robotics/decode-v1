package org.firstinspires.ftc.teamcode.config.core.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public interface IntakeConfiguration {
    String INTAKE_MOTOR = "intake";
    String LIFT_SERVO = "trigger";
    String TRANSFER_MOTOR = "transfer";
    double INTAKE_SPEED = 0.4;
    double TRANSFER_SPEED = 0.5;
    double LIFT_MIN = 0.36;
    double LIFT_MAX = 0.42;
    double LIFT_DOWN_POS = 0.0;
    double LIFT_HOLD_POS = 0.4;
    double LIFT_UP_POS = 1;
}
