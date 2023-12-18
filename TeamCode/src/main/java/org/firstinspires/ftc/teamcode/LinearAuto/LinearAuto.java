package org.firstinspires.ftc.teamcode.LinearAuto;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;

import org.firstinspires.ftc.teamcode.Autos.TfodAutoBluePark;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Runs the given {@link AutoStep}s in order. This Class extends {@link  LinearOpMode},
 * and is responsible for passing in the {@link AutoStep#hardwareMap HardwareMap} and {@link AutoStep#telemetry Telemetry} instances for {@link AutoStep}
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
    public void runOpMode() {

        for (AutoStep step : stepSeries.steps) {
            if (step.runOnInit) AutoStep.initializeStep(step,hardwareMap,telemetry);
        }

        waitForStart();
        try {
            AutoStep.runStep(stepSeries, hardwareMap, telemetry);
        } catch (Exception e) {
            telemetry.addData("stackTrace",Arrays.toString(e.getStackTrace()));
            telemetry.update();
            sleep(10000);
        }
        requestOpModeStop();
    }
}
