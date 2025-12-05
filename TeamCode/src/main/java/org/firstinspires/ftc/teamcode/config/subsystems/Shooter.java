package org.firstinspires.ftc.teamcode.config.subsystems;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter extends SubsystemBase implements ShooterConfiguration {
    private final MotorEx shooter;
    private final JoinedTelemetry telemetry;
    private final Servo hood;
    boolean isShooterOn = false;
    double shooter_rpm;
    private final Servo lift;
    private double liftAngle;
    //Hood is kinda useless rn
    private double hoodPos;
    private double HOOD_STEP = 0.005;


    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        lift = hardwareMap.get(Servo.class, LIFT_SERVO);

        this.shooter_rpm = 0;

        hood = hardwareMap.get(Servo.class, SHOOTER_HOOD);
        shooter = new MotorEx(hardwareMap, SHOOTER_MOTOR, Motor.GoBILDA.BARE);

        shooter.setRunMode(Motor.RunMode.VelocityControl);
        shooter.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        hood.setPosition(0.1);
        hoodPos = 0.1;


        this.telemetry = new JoinedTelemetry(
                PanelsTelemetry.INSTANCE.getFtcTelemetry(),
                telemetry
        );

        setLift(LIFT_DOWN_POS);
    }

    private static class Preset{
        final protected double rpm, hoodPos;
        protected Preset(double rpm, double hoodPos){
            this.rpm = rpm;
            this.hoodPos = hoodPos;
        }
    }
    private final Preset[] presets = new Preset[]{
        new Preset(1850, 0.10),
        new Preset(2150, 0.16),
        new Preset(2650, 0.18)
    };
    public void shooterPreset(int index)
    {
        shooter.setVelocity(presets[index].rpm*multiplierRPM);
        hoodPos = presets[index].hoodPos;
        hood.setPosition(hoodPos);
    }

    //TESTING
    public void hoodUp() {
          hoodPos = Range.clip(hoodPos + HOOD_STEP, 0, 1);
          hood.setPosition(hoodPos);
      }
      public void hoodDown() {
          hoodPos = Range.clip(hoodPos - HOOD_STEP, 0, 1);
          hood.setPosition(hoodPos);
    }
    public void shooterUp()
    {
        shooter_rpm = Range.clip(shooter_rpm + 50, 0, 2800);
        shooter.setVelocity(shooter_rpm*multiplierRPM);
    }
    public void shooterDown()
    {
        shooter_rpm = Range.clip(shooter_rpm - 50, 0, 2800);
        shooter.setVelocity(shooter_rpm*multiplierRPM);
    }


//    public void shootMax() {
//        shooter.setVelocity(shooter.getMaxRPM());
//    }
    public void stopShooter(){
        isShooterOn = false;
        shooter.stopMotor();
    }
    public void telemetry()
    {
        telemetry.addData("Shooter motor velocity: ", shooter.getVelocity());
        telemetry.addData("Hood spin: ", hoodPos);
        telemetry.addData("Lift angle: ", getMappedLift());
        telemetry.addData("Lift angle raw: ", liftAngle);
        telemetry.addData("Shooter rpm: ", shooter_rpm);
        telemetry.update();
    }
    public boolean isShooterOn() {
        return isShooterOn;
    }

    public double getMappedLift(){
        return Range.scale(liftAngle, LIFT_MIN, LIFT_MAX, 0, 1);
    }
    public void setLift(double mappedAngle){
        double clippedMappedAngle = Range.clip(mappedAngle, 0, 1);
        liftAngle = Range.scale(clippedMappedAngle, 0, 1, LIFT_MIN, LIFT_MAX);
        lift.setPosition(liftAngle);
    }
    public void setLiftRel(double mappedAngleChange){
        setLift(getMappedLift()+mappedAngleChange);
    }
}
