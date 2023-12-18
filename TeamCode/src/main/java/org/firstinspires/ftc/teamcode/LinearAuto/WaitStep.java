package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitStep extends AutoStep{
    double timeToWait;
    ElapsedTime time = new ElapsedTime();
    public WaitStep(double seconds) {
        timeToWait = seconds;
    }
    @Override
    public void init() {
        time.reset();
    }

    @Override
    public void run() {
        setFinished(time.seconds() >= timeToWait);
    }

    @Override
    protected void onFinish() {}
}
