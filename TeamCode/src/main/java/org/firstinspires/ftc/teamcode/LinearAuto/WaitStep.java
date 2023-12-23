package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitStep extends AutoStep{
    double timeToWait;
    ElapsedTime time = new ElapsedTime();

    /**
     * "runs" for the specified amount of time, then finishes, acting like a wait.
     * This step will work {@link RunSynchronously synchronously} with other steps, only affecting the {@link StepSeries "series"} its within
     * @param seconds the amount of time (in seconds) that this step runs for
     */
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
