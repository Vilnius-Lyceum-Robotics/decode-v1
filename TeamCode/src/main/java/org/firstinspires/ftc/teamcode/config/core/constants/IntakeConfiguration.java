package org.firstinspires.ftc.teamcode.config.core.constants;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class IntakeConfiguration {
    public static String INTAKE_MOTOR = "intake";
    public static String CONVEYOR_BELT_MOTOR = "transfer";
    public static String LIFT_SERVO = "booster";
    public static double INTAKE_SPEED = 0.4;
    public static double CONVEYOR_SPEED = 0.4;
    public static double LIFT_MIN = 0;
    public static double LIFT_MAX = 1;
}
