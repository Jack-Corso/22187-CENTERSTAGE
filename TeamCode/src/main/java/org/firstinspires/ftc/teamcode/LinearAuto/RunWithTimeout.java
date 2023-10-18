package org.firstinspires.ftc.teamcode.LinearAuto;

public class RunWithTimeout extends AutoStep{
    WaitStep waitStep;
    AutoStep step;
    public RunWithTimeout(AutoStep step, double seconds) {
        this.step = step;
        runOnInit = step.runOnInit;
        waitStep = new WaitStep(seconds);
    }
    @Override
    public void init() {
        step.setHardWareMap(hardwareMap);
        step.setTelemetry(telemetry);
        step.init();
        waitStep.init();
    }

    @Override
    public void run() {
        waitStep.run();
        step.run();
        setFinished(waitStep.isFinished() || step.isFinished());
    }

    @Override
    protected void onFinish() {
        step.onFinish();
        waitStep.onFinish();
    }
}
