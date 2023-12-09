package org.firstinspires.ftc.teamcode.LinearAuto;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;

import org.firstinspires.ftc.teamcode.Autos.TfodAutoBluePark;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Runs the given {@link AutoStep}s in order, calling steps
 */
public abstract class LinearAuto extends LinearOpMode implements Stepable{
    StepSeries stepSeries;
    public LinearAuto(Stepable... steps) {
        stepSeries = new StepSeries(steps);
    }
    @Override
    public final AutoStep toAutoStep() {
        return stepSeries;
    }
    @Override
    public void runOpMode() throws InterruptedException {
        for (AutoStep step : stepSeries.steps) {
            if (step.runOnInit) AutoStep.initializeStep(step,hardwareMap,telemetry);
        }
        waitForStart();
        AutoStep.runStep(stepSeries,hardwareMap,telemetry);
        requestOpModeStop();
    }
}
