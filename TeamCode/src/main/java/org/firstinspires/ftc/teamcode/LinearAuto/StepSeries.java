package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.Blinker;

public class StepSeries extends AutoStep{
    //todo make LinearAuto use this (its pretty much the same code)
    AutoStep[] steps;
    int currentStep = 0;
    int totalSteps;
    public StepSeries(AutoStep... steps) {
        this.steps = steps;
        runOnInit = true;
        totalSteps = steps.length-1;
    }
    @Override
    public void init() {
        for (AutoStep step : steps) {
            if (step.runOnInit) {
                step.setTelemetry(telemetry);
                step.setHardWareMap(hardwareMap);
                step.init();
                step.initDone = true;
            }
        }
    }

    @Override
    public void run() {
        if (!steps[currentStep].initDone) {
            steps[currentStep].setTelemetry(telemetry);
            steps[currentStep].setHardWareMap(hardwareMap);
            steps[currentStep].init();
            steps[currentStep].initDone = true;
        }
        if (steps[currentStep].isFinished()) {
            steps[currentStep].onFinish();
            setFinished(currentStep == totalSteps);
            currentStep++;
        } else {
            steps[currentStep].run();
        }

    }

    @Override
    protected void onFinish() {

    }
}
