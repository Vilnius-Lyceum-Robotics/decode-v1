package org.firstinspires.ftc.teamcode.config.pedroPathing;

import com.pedropathing.geometry.Pose;

public class PointsFar {
    public static Pose getGoalPose(double x, double y){
        return new Pose(x, y, Math.toRadians(90) -Math.atan2(0-x, 144-y));
    }
    public static final Pose START_POS = new Pose(55, 8.2, Math.toRadians(90));
    public static final Pose EXIT_POS = new Pose(55, 35, Math.toRadians(90));
    public static final Pose SHOOT_POS = new Pose(55, 40, Math.toRadians(135));
    public static final Pose SHOOT_POS1 = new Pose(0, 8.2, Math.toRadians(90));
    public static final Pose SHOOT_POS2 = new Pose(0, 40, Math.toRadians(90));
    public static final Pose SHOOT_POS3 = new Pose(55, 120, Math.toRadians(90));

    //INTAKE_1
    public static final Pose PREPARE_TO_INTAKE_POS_1 = new Pose(36, 84, Math.toRadians(180));
    public static final Pose FINAL_INTAKE_POS_1 = new Pose(20, 84, Math.toRadians(180));

    //INTAKE_2
    //public static final Pose PREPARE_TO_INTAKE_POS_2 = new Pose(36, 84, Math.toRadians(180));
    //public static final Pose FINAL_INTAKE_POS_1 = new Pose(20, 84, Math.toRadians(180));

    //INTAKE_3
    //public static final Pose PREPARE_TO_INTAKE_POS_1 = new Pose(36, 84, Math.toRadians(180));
    //public static final Pose FINAL_INTAKE_POS_1 = new Pose(20, 84, Math.toRadians(180));
}
