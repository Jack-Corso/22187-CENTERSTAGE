package org.firstinspires.ftc.teamcode.LinearAuto;

import java.util.function.Supplier;

public class ToTelemetry extends AutoStep{

    Supplier<String> value;
    boolean continuous;
    public ToTelemetry(Supplier<String> value,boolean continuous) {
        this.value = value;
        this.continuous = continuous;
    }

    @Override
    public void init() {}

    @Override
    public void run() {
        telemetry.addLine(value.get());
        telemetry.update();
        setFinished(!continuous);
    }

    @Override
    protected void onFinish() {}
}
