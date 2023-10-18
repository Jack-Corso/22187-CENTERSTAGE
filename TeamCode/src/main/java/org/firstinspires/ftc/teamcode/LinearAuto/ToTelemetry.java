package org.firstinspires.ftc.teamcode.LinearAuto;

public class ToTelemetry extends AutoStep{
    String value;
    boolean continuous;
    public ToTelemetry(String value,boolean continuous) {
        this.value = value;
        this.continuous = continuous;
    }

    @Override
    public void init() {}

    @Override
    public void run() {
        telemetry.addLine(value);
        telemetry.update();
        setFinished(!continuous);
    }

    @Override
    protected void onFinish() {}
}
