package org.firstinspires.ftc.teamcode.config.pedroPathing;

import com.pedropathing.geometry.Pose;

public class PointsFar {
    public static Pose getGoalPose(double x, double y){
        return new Pose(x, y, Math.atan2(0-x, 144-y));
    }
    public static final Pose START_POS = new Pose(60, 8.2, Math.toRadians(135));
    public static final Pose SHOOT_POS = getGoalPose(60, 12);
    
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
