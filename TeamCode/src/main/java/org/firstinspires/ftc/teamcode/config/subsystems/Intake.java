package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private Motor intake;
    private double intakeSpeed = INTAKE_SPEED;
    private Motor conveyor;
    private double conveyorSpeed = CONVEYOR_SPEED;
    private Servo lift;
    private double liftAngle;
    private Telemetry telemetry;
    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        intake = new MotorEx(hardwareMap, INTAKE_MOTOR, Motor.GoBILDA.RPM_435);
        conveyor = new MotorEx(hardwareMap, CONVEYOR_BELT_MOTOR, Motor.GoBILDA.RPM_435);
        lift = hardwareMap.get(Servo.class, LIFT_SERVO);

        intake.setInverted(true);
        conveyor.setInverted(true);

        intake.setRunMode(Motor.RunMode.RawPower);
        conveyor.setRunMode(Motor.RunMode.RawPower);

        intake.set(0);
        conveyor.set(0);
        setLift(0);
    }
    public void setIntake(boolean on){
        intake.set(on ? intakeSpeed : 0);
    }
    public void setIntakeSpeed(double speed){
        intakeSpeed = Range.clip(speed, 0, 1);
    }
    public void setIntakeSpeedRel(double change){
        setIntakeSpeed(intakeSpeed + change);
    }

    public void setConveyor(boolean on){
        conveyor.set(on ? conveyorSpeed : 0);
    }
    public void setConveyorSpeed(double speed){
        conveyorSpeed = Range.clip(speed, 0, 1);
    }
    public void setConveyorSpeedRel(double change){
        setConveyorSpeed(conveyorSpeed + change);
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
        telemetry.addData("Intake speed: ", intakeSpeed);
        telemetry.addData("Conveyor speed: ", conveyorSpeed);
    }
}
