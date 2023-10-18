package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class LinearAuto extends LinearOpMode {
    //todo make this use StepSeries (its basically the same code)
    AutoStep[] steps;
    int totalSteps = 0;
    public LinearAuto(AutoStep... steps) {
        this.steps = steps;
        totalSteps = steps.length-1;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        int currentStep = 0;
        for (AutoStep step : steps) {
            if (step.runOnInit) {
                step.setHardWareMap(hardwareMap);
                step.setTelemetry(telemetry);
                step.init();
                step.initDone = true;
            }
        }
        waitForStart();
        while (opModeIsActive()) {
            if (!steps[currentStep].initDone) {
                steps[currentStep].setTelemetry(telemetry);
                steps[currentStep].setHardWareMap(hardwareMap);
                steps[currentStep].init();
                steps[currentStep].initDone = true;
            }
            steps[currentStep].run();
            if (steps[currentStep].isFinished()) {
                steps[currentStep].onFinish();
                if (currentStep < totalSteps) {
                    currentStep++;
                } else {
                    requestOpModeStop();
                }
            }

        }
    }
}
