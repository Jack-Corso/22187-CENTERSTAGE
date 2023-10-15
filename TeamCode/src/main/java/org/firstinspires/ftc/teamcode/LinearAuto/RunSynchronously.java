package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;

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
                finishedSteps++;
                setFinished(finishedSteps == steps.length-1);

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
