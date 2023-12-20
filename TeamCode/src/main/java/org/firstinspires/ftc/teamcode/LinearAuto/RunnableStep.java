package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Calls the {@link Runnable#run()} method of the given {@link Runnable} once, then finishes
 */
public class RunnableStep extends AutoStep{
    Runnable runnable = null;
    BiConsumer<HardwareMap,Telemetry> biConsumer = null;
    public RunnableStep(Runnable r) {
        runnable = r;
    }

    /**
     * Instead of using a runnable, this Constructor uses a {@link BiConsumer<HardwareMap> BiConsumer},
     * allowing for this steps {@link #hardwareMap HardwareMap instance} and {@link #telemetry Telemetry instance} to be used inside the functionalInterface.
     * <p></p>
     * NOTE: the lambda expression used for this would look like (hardwareMap,telemetry)->{hardwareMap.get(...); telemetry.addData(...);}.
     * The name in the parenthesis represents the passed in HardwareMap/Telemetry, and can have any name, not just hardwareMap and telemetry as shown in the example
     * @param biConsumer the functionalInterface to be run
     */
    public RunnableStep(BiConsumer<HardwareMap,Telemetry> biConsumer) {
        this.biConsumer = biConsumer;
    }
    @Override
    public void init() {

    }

    @Override
    public void run() {
        if (Objects.isNull(runnable)) {
            biConsumer.accept(hardwareMap,telemetry);
        } else {
            runnable.run();
        }
        setFinished(true);
    }

    @Override
    protected void onFinish() {

    }
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }
    public Telemetry getTelemetry() {
        return telemetry;
    }
}
