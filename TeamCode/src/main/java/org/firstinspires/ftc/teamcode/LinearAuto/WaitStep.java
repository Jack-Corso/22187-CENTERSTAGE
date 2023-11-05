package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.Servo;

public class WaitStep extends AutoStep{
    long timeToWait;
    long startTime;
    public WaitStep(double seconds) {
        timeToWait = (long) (seconds*1000);
    }
    @Override
    public void init() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        setFinished(System.currentTimeMillis()-startTime >= timeToWait);
    }

    @Override
    protected void onFinish() {}
}
