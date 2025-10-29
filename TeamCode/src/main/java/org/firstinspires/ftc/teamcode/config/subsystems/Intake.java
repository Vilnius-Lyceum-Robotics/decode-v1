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
import org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration;

public class Intake extends SubsystemBase implements IntakeConfiguration {
    private final Motor intake;
    private double intakeSpeed = INTAKE_SPEED;
    private boolean isIntakeOn = false;
    private final Servo lift;
    private double liftAngle;
    private final JoinedTelemetry telemetry;
    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);

        intake = new MotorEx(hardwareMap, INTAKE_MOTOR, Motor.GoBILDA.RPM_435);
        lift = hardwareMap.get(Servo.class, LIFT_SERVO);

        intake.setInverted(true);

        intake.setRunMode(Motor.RunMode.RawPower);

        intake.set(0);
        setLift(LIFT_DOWN_POS);
    }
    public void setIntake(boolean on){
        isIntakeOn = on;
        intake.set(on ? intakeSpeed : 0);
    }
    public void setIntakeSpeed(double speed){
        intakeSpeed = Range.clip(speed, -1, 1);
        if(isIntakeOn){
            intake.set(intakeSpeed);
        }
    }
    public void setIntakeSpeedRel(double change){
        setIntakeSpeed(intakeSpeed + change);
    }
    public double getIntakeSpeed(){
        return intakeSpeed;
    }
    public double getRealLift(){
        return liftAngle;
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

    public void telemetry(){
        telemetry.addData("Lift angle: ", getMappedLift());
        telemetry.addData("Lift angle raw: ", liftAngle);
        telemetry.addData("Intake speed: ", intakeSpeed);
//        telemetry.addData("Conveyor speed: ", conveyorSpeed);
    }

    public boolean isIntakeOn() {
        return isIntakeOn;
    }
}
