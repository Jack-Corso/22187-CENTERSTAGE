package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class AutoStep implements Runnable{
    protected HardwareMap hardwareMap = null;
    protected Telemetry telemetry = null;
    private boolean isFinished = false;
    protected boolean runOnInit = false;
    public abstract void init();
    @Override
    public abstract void run();
    protected abstract void onFinish();
    final void setHardWareMap(HardwareMap map) {
        hardwareMap = map;
    }
    final void setTelemetry(Telemetry tele) {telemetry = tele;}
    final boolean isFinished() {
        return isFinished;
    }
    public final void setFinished(boolean val) {
        isFinished = val;
    }
}
