package org.firstinspires.ftc.teamcode.LinearAuto;

import org.firstinspires.ftc.teamcode.R;

public class RunIf extends AutoStep{
    boolean condition;
    AutoStep step;
    //TODO try using BooleanSupplier?
    public RunIf(boolean condition, AutoStep step) {
        setFinished(!condition);
        this.condition = condition;
        this.step = step;
        runOnInit = step.runOnInit;
    }

    @Override
    public void init() {
        if (condition) {
            step.setHardWareMap(hardwareMap);
            step.setTelemetry(telemetry);
            step.init();
        }
    }

    @Override
    public void run() {
        if (condition) step.run();
        setFinished(step.isFinished() || !condition);
    }

    @Override
    protected void onFinish() {
        if (condition) step.onFinish();

    }
    public RunIf elseIfDo(boolean condition, AutoStep step) {
        if (!this.condition) {
            return new RunIf(condition, step);
        }
        return this;
    }
    public AutoStep elseDo(AutoStep step) {
        if (!condition) return step;
        else return this;
    }
}
