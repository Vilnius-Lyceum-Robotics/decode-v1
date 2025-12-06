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

import java.util.HashSet;

public class Intake extends SubsystemBase implements IntakeConfiguration {
    private final Motor intake, transfer;
    private double intakeSpeed = INTAKE_SPEED;
    private double transferSpeed = TRANSFER_SPEED;
    private boolean isIntakeOn = false;
    private boolean isTransferOn = false;
    private BooleanHolder transferBooleanHolder = new BooleanHolder();
    private final JoinedTelemetry telemetry;
    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);

        intake = new MotorEx(hardwareMap, INTAKE_MOTOR, Motor.GoBILDA.BARE);
        transfer = new MotorEx(hardwareMap, TRANSFER_MOTOR, Motor.GoBILDA.BARE);

        intake.setRunMode(Motor.RunMode.RawPower);

        intake.set(0);
    }
    private static class BooleanHolder {
        private HashSet<Integer> onInds = new HashSet<>();

        public void setBool(int ind, boolean bool) {
            if(bool){
                onInds.add(ind);
            }else{
                onInds.remove(ind);
            }
        }
        public boolean getValue(){
            return onInds.size() > 0;
        }
    };
    public void setIntake(boolean on){
        isIntakeOn = on;
        intake.set(on ? intakeSpeed : 0);
    }
    public void setTransfer(int ind, boolean on){
        transferBooleanHolder.setBool(ind, on);
        isTransferOn = transferBooleanHolder.getValue();
        if(isTransferOn){
            transfer.set(transferSpeed);
        }else{
            transfer.stopMotor();
        }
    }
    public void setTransfer(boolean on){
        setTransfer(0, on);
    }
    public void setIntakeTransfer(int dir){
        dir = Range.clip(dir, -1, 1);
        if(dir == 0){
            setTransfer(false);
            setIntake(false);
            return;
        }
        transfer.setInverted(dir == -1);
        setTransfer(true);
        setIntake(true);
    }
    public void setIntakeSpeed(double speed){
        intakeSpeed = Range.clip(speed, -1, 1);
        intake.set(intakeSpeed);
        isIntakeOn = true;
    }
    public boolean isTransferOn() {return isTransferOn;}
    public void setIntakeSpeedRel(double change){
        setIntakeSpeed(intakeSpeed + change);
    }
    public double getIntakeSpeed(){
        return intakeSpeed;
    }

    public void telemetry(){
        telemetry.addData("Intake speed: ", intakeSpeed);
    }

    public boolean isIntakeOn() {
        return isIntakeOn;
    }
}
