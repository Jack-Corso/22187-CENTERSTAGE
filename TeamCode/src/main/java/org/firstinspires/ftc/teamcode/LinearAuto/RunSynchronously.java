package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;

import java.util.Arrays;
import java.util.Objects;

public class RunSynchronously extends AutoStep {
    AutoStep[] steps;
    public RunSynchronously(AutoStep... steps) {
        this.steps = steps;
        runOnInit = true;
    }

    @Override
    public void init() {
        for (AutoStep step : steps) {
            step.setHardWareMap(hardwareMap);
            step.setTelemetry(telemetry);
            if (step.runOnInit) {
                step.init();
                step.initDone = true;
            }


        }
    }

    @Override
    public void run() {

        for (int i = 0; i < steps.length; i++) {
            AutoStep step = steps[i];
            if (Objects.isNull(step)) continue;
            if (!step.initDone) {
                step.init();
                step.initDone = true;
            }

            if (!step.isFinished()) step.run();
            else {
                step.onFinish();
                steps[i] = null;
            }
        }
        setFinished(Arrays.stream(steps).allMatch(Objects::isNull));
    }
    @Override
    protected void onFinish() {
        for (AutoStep step : steps) {
            step.onFinish();

        }
    }
}
