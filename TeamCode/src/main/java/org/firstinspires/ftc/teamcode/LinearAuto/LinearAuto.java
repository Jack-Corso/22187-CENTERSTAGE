package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class LinearAuto extends LinearOpMode {
    AutoStep[] steps;
    int totalSteps = 0;
    boolean initDone = false;
    public LinearAuto(AutoStep... steps) {
        this.steps = steps;
        totalSteps = steps.length-1;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        int currentStep = 0;
        for (AutoStep step : steps) {
            if (step.runOnInit) {
                step.init();
            }
        }
        waitForStart();
        while (opModeIsActive()) {
            if (!initDone) {
                steps[currentStep].setTelemetry(telemetry);
                steps[currentStep].setHardWareMap(hardwareMap);
                steps[currentStep].init();
                initDone = true;
            }
            steps[currentStep].run();
            if (steps[currentStep].isFinished()) {
                steps[currentStep].onFinish();
                initDone = false;
                if (currentStep < totalSteps) {
                    currentStep++;
                } else {
                    requestOpModeStop();
                }
            }

        }
    }
}
