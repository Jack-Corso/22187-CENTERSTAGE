package org.firstinspires.ftc.teamcode.LinearAuto;

/**
 * Runs a step fully during the initialization section of an OpMode
 */
public class InitStep extends AutoStep{
    AutoStep step;
    public InitStep(AutoStep step) {
        runOnInit = true;
        this.step = step;
    }
    @Override
    public void init() {
        AutoStep.runStep(step,hardwareMap,telemetry);
        setFinished(true);
    }

    @Override
    public void run() {

    }

    @Override
    protected void onFinish() {

    }
}
