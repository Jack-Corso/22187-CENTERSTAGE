package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Runs the given step, either until the step is {@link AutoStep#isFinished() finished}, or until the given amount of time has passed, then calls the steps {@link AutoStep#onFinish()}
 */
public class RunWithTimeout extends AutoStep{
    double seconds;
    AutoStep step;
    ElapsedTime time = new ElapsedTime();
    boolean started = false;
    public RunWithTimeout(AutoStep step, double seconds) {
        this.step = step;
        runOnInit = step.runOnInit;
        this.seconds = seconds;
    }
    @Override
    public void init() {
        step.setHardWareMap(hardwareMap);
        step.setTelemetry(telemetry);
        step.init();
        if (!runOnInit) {
            started = true;
            time.reset();
        }
    }

    @Override
    public void run() {
        if (runOnInit && !started) {
            time.reset();
            started = true;
        }
        step.run();
        setFinished(time.seconds() >= seconds || step.isFinished());
    }

    @Override
    protected void onFinish() {
        step.onFinish();

    }
}
