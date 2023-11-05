package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;

import java.util.Arrays;

public class RunSynchronously extends AutoStep {
    AutoStep[] steps;
    public RunSynchronously(AutoStep... steps) {
        this.steps = steps;
    }

    @Override
    public void init() {
        for (AutoStep step : steps) {
            step.setHardWareMap(hardwareMap);
            step.init();
        }
    }

    @Override
    public void run() {
        int finishedSteps = 0;
        for (AutoStep step : steps) {
            if (!step.isFinished()) {
                step.run();
            }
            else {
                setFinished(Arrays.stream(steps).allMatch(AutoStep::isFinished));
            }

        }
    }
    @Override
    protected void onFinish() {
        for (AutoStep step : steps) {
            step.onFinish();

        }
    }
}
