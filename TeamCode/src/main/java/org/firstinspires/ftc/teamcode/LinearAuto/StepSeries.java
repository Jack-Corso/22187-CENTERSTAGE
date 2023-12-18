package org.firstinspires.ftc.teamcode.LinearAuto;


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
