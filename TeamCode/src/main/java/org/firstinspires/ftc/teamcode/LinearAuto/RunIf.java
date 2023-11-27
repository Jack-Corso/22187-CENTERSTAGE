package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.R;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class RunIf extends AutoStep{
    BooleanSupplier condition;
    AutoStep step;
    ArrayList<RunIf> tree = new ArrayList<>();
    boolean scannedTree = false;

    public RunIf(BooleanSupplier condition, AutoStep step) {
        this.condition = condition;
        this.step = step;
        //runOnInit = step.runOnInit;
    }

    @Override
    public void init() {
        step.setTelemetry(telemetry);
        step.setHardWareMap(hardwareMap);
        for (RunIf runif : tree) {
            runif.step.setTelemetry(telemetry);
            runif.step.setHardWareMap(hardwareMap);
        }
        if (!condition.getAsBoolean()) {
            for (RunIf runIf : tree) {
                telemetry.addData("at branch", tree.indexOf(runIf));
                telemetry.addLine(String.valueOf(runIf.getCondition().getAsBoolean()));
                if (runIf.getCondition().getAsBoolean()) {
                    step = runIf.step;
                    condition = runIf.getCondition();
                    return;
                }

            }
            scannedTree = true;
        }
        telemetry.update();
        if (condition.getAsBoolean()) {
            step.setHardWareMap(hardwareMap);
            step.setTelemetry(telemetry);
            step.init();
        }
    }

    @Override
    public void run() {
        if (condition.getAsBoolean()) step.run();
        setFinished(step.isFinished() || !condition.getAsBoolean());
    }

    @Override
    protected void onFinish() {
       if (condition.getAsBoolean()) step.onFinish();

    }
    public RunIf elseIfDo(BooleanSupplier condition, AutoStep step) {
        tree.add(new RunIf(condition,step));
        return this;
    }
    public RunIf elseDo(AutoStep step) {
        tree.add(new RunIf(()->true,step));
        return this;
    }
    public BooleanSupplier getCondition() {
        return condition;
    }
}
