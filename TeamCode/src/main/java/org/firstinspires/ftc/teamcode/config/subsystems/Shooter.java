package org.firstinspires.ftc.teamcode.config.subsystems;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.command.WaitCommand;
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

    int RPM_upper;
    int RPM_lower;

    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        this.RPM_upper = 0;
        this.RPM_lower = 0;

        this.upperPercentage = 0;
        this.lowerPercentage = 0;

        upper = new MotorEx(hardwareMap, SHOOTER_UPPER, Motor.GoBILDA.BARE);
        lower = new MotorEx(hardwareMap, SHOOTER_LOWER, Motor.GoBILDA.BARE);

        //lower.setInverted(true);
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
    public void increaseUpperRPM(int RPM) {
        RPM_upper = Math.min(RPM_upper + RPM, (int) upper.getMaxRPM());
        upper.setVelocity(RPM_upper);
    }

    public void increaseLowerRPM(int RPM) {
        RPM_lower = Math.min(RPM_lower + RPM, (int) lower.getMaxRPM());
        lower.setVelocity(RPM_lower);
    }

    public void decreaseUpperRPM(int RPM)
    {
        RPM_upper = Math.max(0, RPM_upper - RPM);
        upper.setVelocity(RPM_upper);
    }
    public void decreaseLowerRPM(int RPM)
    {
        RPM_lower = Math.max(0, RPM_lower - RPM);
        lower.setVelocity(RPM_lower);
    }

    public void shootLow()
    {
        upper.setVelocity(1500.0*3/4);
        lower.setVelocity((1500.0*3/4));
    }
    public void shootHigh()
    {
        upper.setVelocity(1800.0*3/4);
        lower.setVelocity(1800.0*3/4);
    }
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
