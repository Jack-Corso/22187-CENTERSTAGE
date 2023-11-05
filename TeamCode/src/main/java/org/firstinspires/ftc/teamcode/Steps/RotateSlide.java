package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.PIDController;

public class RotateSlide extends AutoStep {
    PIDController PID = new PIDController(0.004,0,0.001);
    DcMotor m;
    public RotateSlide(int pos) {
        PID.setTargetPos(pos);
    }
    @Override
    public void init() {
        m = hardwareMap.dcMotor.get("rotateSlide");
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run() {
        m.setPower(PID.motorSpeed(m.getCurrentPosition()));
        setFinished(PID.isFinished());
    }

    @Override
    protected void onFinish() {
        m.setPower(0);
    }
}
