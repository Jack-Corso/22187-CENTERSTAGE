package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;

import java.util.Arrays;
import java.util.Objects;

/**
 * Runs the given steps "at the same time", this does NOT use multithreading,
 * instead calling the appropriate method for each unfinished step repeatedly. Once all given steps are finished, this step also finishes.
 * <p>
 * Note: steps methods are called in the order the steps were passed in, and {@link AutoStep#onFinish()} is called directly after a step finishes, not after all steps finish
 */
public class RunSynchronously extends AutoStep {
    AutoStep[] steps;
    AutoStep[] removed;
    public RunSynchronously(Stepable... steps) {
        this.steps = Stepable.toAutoStepArray(steps);
        runOnInit = true;
        removed = new AutoStep[steps.length];
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
                removed[i] = step;
                steps[i] = null;
            }
        }
        setFinished(Arrays.stream(steps).allMatch(Objects::isNull));
    }
    @Override
    protected void onFinish() {
        for (AutoStep step : removed) {
            step.onFinish();
        }
    }
}
