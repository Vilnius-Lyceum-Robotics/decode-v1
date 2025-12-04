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
    boolean isShooterOn = false;
    int shooter_rpm;
    private final Servo lift;
    private double liftAngle;
    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        lift = hardwareMap.get(Servo.class, LIFT_SERVO);

        this.shooter_rpm = 0;

        shooter = new MotorEx(hardwareMap, SHOOTER_MOTOR, Motor.GoBILDA.BARE);

        shooter.setRunMode(Motor.RunMode.VelocityControl);
        shooter.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        this.telemetry = new JoinedTelemetry(
                PanelsTelemetry.INSTANCE.getFtcTelemetry(),
                telemetry
        );

        setLift(LIFT_DOWN_POS);
    }

    public void shootLow()
    {
        shooter.setVelocity(1500.0 * multiplierRPM);
    }
    public void shootHigh()
    {
        shooter.setVelocity(1800.0 * multiplierRPM);
    }
    public void shoot(){
        isShooterOn = true;
        shootMax();
    }
    public void shootMax() {
        shooter.setVelocity(shooter.getMaxRPM());
    }
    public void stop(){
        isShooterOn = false;
        shooter.stopMotor();
    }
    public void telemetry()
    {
        telemetry.addData("Shooter motor velocity: ", shooter.getVelocity());
        telemetry.addData("Lift angle: ", getMappedLift());
        telemetry.addData("Lift angle raw: ", liftAngle);
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
    /*
    public void setUpperVelocity(double value) {
        upperPercentage = Range.clip(value, 0, 1);
        upper.setVelocity(upper.getMaxRPM() * upperPercentage);
    }

    public void setLowerVelocity(double value) {
        lowerPercentage = Range.clip(value, 0, 1);
        double speedToUse = Math.max(lowerPercentage, isLowSpin ? LOW_SPIN_FORCE : 0);
        if (speedToUse <= 1e-6) {
            lower.stopMotor();
        } else {
            lower.setVelocity(lower.getMaxRPM() * speedToUse);
        }
    }

    // FOR TESTING
    public void increaseUpperRPM(int RPM) {
        shooter_rpm = Math.min(shooter_rpm + RPM, (int) upper.getMaxRPM());
        upper.setVelocity(shooter_rpm);
    }

    public void increaseLowerRPM(int RPM) {
        RPM_lower = Math.min(RPM_lower + RPM, (int) lower.getMaxRPM());
        lower.setVelocity(RPM_lower);
    }

    public void decreaseUpperRPM(int RPM)
    {
        shooter_rpm = Math.max(0, shooter_rpm - RPM);
        upper.setVelocity(shooter_rpm);
    }
    public void decreaseLowerRPM(int RPM)
    {
        RPM_lower = Math.max(0, RPM_lower - RPM);
        lower.setVelocity(RPM_lower);
    }

    public void shootLow()
    {
        upper.setVelocity(1500.0 * multiplierRPM);
        lower.setVelocity((1500.0 * multiplierRPM));
        isShooterOn = true;
    }
    public void shootHigh()
    {
        upper.setVelocity(1800.0*multiplierRPM);
        lower.setVelocity(1800.0*multiplierRPM);
    }
    public void shoot(){
        isShooterOn = true;
        shootHigh();
    }
    public void stop(){
        isShooterOn = false;
        upper.stopMotor();
        lower.stopMotor();
        lowerPercentage = 0;
        upperPercentage = 0;
    }
    public void setLowSpin(boolean lowSpin){
        isLowSpin = lowSpin;
        setLowerVelocity(lowerPercentage); //Low spin isn't reflected in lowerPercentage
    }
    public void changeLowerForce(double amount) {
        this.lowerForce += amount;
    }

    public void changeUpperForce(double amount) {
        this.upperForce += amount;
    }

    public double getUpperForce() {
        return upperForce;
    }

    public double getLowerForce() {
        return lowerForce;
    } */
}
