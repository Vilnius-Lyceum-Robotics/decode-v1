package org.firstinspires.ftc.teamcode.config.subsystems;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter extends SubsystemBase implements ShooterConfiguration {
    double upperPercentage;
    double lowerPercentage;
    private final MotorEx upper, lower;
    private final JoinedTelemetry telemetry;
    boolean isLowSpin = false;
    boolean isShooterOn = false;

    double lowerForce;
    double upperForce;

    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        this.upperPercentage = 0;
        this.lowerPercentage = 0;

        upper = new MotorEx(hardwareMap, SHOOTER_UPPER, Motor.GoBILDA.BARE);
        lower = new MotorEx(hardwareMap, SHOOTER_LOWER, Motor.GoBILDA.BARE);

        lower.setInverted(true);
        upper.setInverted(true);

        upper.setRunMode(Motor.RunMode.VelocityControl);
        lower.setRunMode(Motor.RunMode.VelocityControl);

        this.telemetry = new JoinedTelemetry(
                PanelsTelemetry.INSTANCE.getFtcTelemetry(),
                telemetry
        );
        this.lowerForce = 1;
        this.upperForce = 1;
    }

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
    public void increaseUpperRPM(int RPM)
    {
        upper.setVelocity(Math.min(upper.getVelocity() + RPM, upper.getMaxRPM()));
    }
    public void increaseLowerRPM(int RPM)
    {
        lower.setVelocity(Math.min(lower.getVelocity() + RPM, lower.getMaxRPM()));
    }
    public void decreaseUpperRPM(int RPM)
    {
        upper.setVelocity(Math.max(0, upper.getVelocity() - RPM));
    }
    public void decreaseLowerRPM(int RPM)
    {
        lower.setVelocity(Math.max(0, lower.getVelocity() - RPM));
    }
    //
    public void shoot(){
        isShooterOn = true;
        lower.set(this.lowerForce);
        upper.set(this.upperForce);
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
    public boolean isShooterOn() {
        return isShooterOn;
    }
    // SIMPLE LAUNCH TO DISTANCE
    /*
    void launchToDistance(int distance)
    {
        double h = 0.000; // initial launch height
        double H = 0.000; // target height
        double r = 0.000; // rotor radius
        double k = 0.000; // slip constant (to tune)

        double velocity = 60*Math.sqrt(9.8 * d * d / (d + h - H))/(2*3.14*r*(1-k)*lower.getMaxRPM());

        shoot(velocity);
    }
     */


    public void telemetry()
    {
        telemetry.addData("Upper motor velocity: ", upper.getVelocity());
        telemetry.addData("Lower motor velocity: ", lower.getVelocity());
        telemetry.update();
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
    }
}
