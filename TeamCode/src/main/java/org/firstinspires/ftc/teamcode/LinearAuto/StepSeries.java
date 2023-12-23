package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This step runs all the given steps in order,
 * waiting for a step to {@link #isFinished() finish} before {@link #runStep(AutoStep, HardwareMap, Telemetry) running} the next step.
 * @see LinearAuto
 */
public class StepSeries extends AutoStep{
    AutoStep[] steps;
    public StepSeries(Stepable... steps) {
        this.steps = Stepable.toAutoStepArray(steps);
        runOnInit = true;

    }
    @Override
    public void init() {
        for (AutoStep step : steps) {
            if (step.runOnInit && !step.initDone) {
                step.setTelemetry(telemetry);
                step.setHardWareMap(hardwareMap);
                step.init();
                step.initDone = true;
            }
        }
    }

    @Override
    public void run() {
        for (AutoStep step : steps) AutoStep.runStep(step,hardwareMap,telemetry);
        setFinished(true);
    }

    @Override
    protected void onFinish() {

    }
}
