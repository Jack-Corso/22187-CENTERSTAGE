package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RunnableStep extends AutoStep{
    Runnable runnable;
    public RunnableStep(Runnable r) {
        runnable = r;
    }
    @Override
    public void init() {

    }

    @Override
    public void run() {
        runnable.run();
        setFinished(true);
    }

    @Override
    protected void onFinish() {

    }
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }
    public Telemetry getTelemetry() {
        return telemetry;
    }
}
