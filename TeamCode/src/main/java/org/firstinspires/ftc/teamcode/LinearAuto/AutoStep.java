package org.firstinspires.ftc.teamcode.LinearAuto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Abstract class that contains multiple methods that are called sequentially in the order: <p>
 *     {@link #init()}, {@link #run()} (looping), {@link #onFinish()}. <p>
 *     The method {@link #runStep(AutoStep, HardwareMap, Telemetry)} can be used to execute a step fully
 */
public abstract class AutoStep implements Runnable, Stepable{
    /**
     * The {@link HardwareMap} instance passed in before the steps initialization. <p>
     * NOTE: This is not passed in on construction, and will be null until {@link #init()}
     * @see #setHardWareMap(HardwareMap)
     */
    protected HardwareMap hardwareMap = null;
    /**
     * The {@link Telemetry} instance passed in before the steps initialization. <p>
     * NOTE: This is not passed in on construction, and will be null until {@link #init()}
     * @see #setTelemetry(Telemetry)
     */
    protected Telemetry telemetry = null;
    /**
     * Used to determine when to stop calling {@link #run()} and call {@link #onFinish()}
     * @see #isFinished()
     * @see #setFinished(boolean)
     */
    private boolean isFinished = false;
    /**
     * used to determine if {@link #init()} should be called on initialization <p>
     * NOTE: This is likely to change to a separate method call in the future
     */
    //TODO change the program to use a separate method for initialization and starting
    protected boolean runOnInit = false;
    /**
     * Used to determine if {@link #init()} has been called already
     */
    boolean initDone = false;
    /**
     * Called when the step first starts, (or on initialization, if {@link #runOnInit} is 'true')
     */
    public abstract void init();

    /**
     * Called in a loop until {@link #setFinished(boolean)} is used to end the step
     */
    @Override
    public abstract void run();

    /**
     * Called at the end of the step, directly after {@link #isFinished()} returns 'true'
     */
    protected abstract void onFinish();

    /**
     * Used to pass in an instance of {@link HardwareMap} for the step to use via {@link #hardwareMap}, called before {@link #init()}
     * @param map the instance passed in
     * @see #hardwareMap
     */
    final void setHardWareMap(HardwareMap map) {
        hardwareMap = map;
    }

    /**
     * Used to pass in an instance of {@link Telemetry} for the step to use via {@link #telemetry}, called before {@link #init()}
     * @param tele the instance passed in
     * @see #telemetry
     */
    final void setTelemetry(Telemetry tele) {telemetry = tele;}

    /**
     * Used to get the value of {@link #isFinished}, when 'true' is returned, {@link #run()} stops being called, and {@link #onFinish()} is called
     * @return if the step is finished or not
     * @see #setFinished(boolean)
     * @see #isFinished
     */
    final boolean isFinished() {
        return isFinished;
    }

    /**
     * Used to set if the step is finished or not, modifies the value of {@link #isFinished}
     * @param val if the step is finished or not
     * @see #isFinished
     * @see #isFinished()
     */
    public final void setFinished(boolean val) {
        isFinished = val;
    }

    /**
     * Runs a given step until finished, passing in a given {@link HardwareMap} and {@link Telemetry} <p>
     * If the step is finished in {@link #init()}, the step with still call {@link #run()} once before finishing <p>
     * NOTE: if the step's {@link #init()} method has already been called, this method does not call it again
     * @param step the step to run
     * @param h the {@link HardwareMap} to be passed into the step
     * @param t the {@link Telemetry} to be passed into the step
     * @see #runStepInLoop(AutoStep, HardwareMap, Telemetry)
     */
    public static void runStep(AutoStep step,HardwareMap h, Telemetry t) {
        while (!runStepInLoop(step,h,t));
    }

    /**
     * Runs a given step's current method once. It is the users responsibility to stop running the step when it is finished, as it will continue to call {@link #onFinish()}. Runs with the same specifications as {@link #runStep(AutoStep, HardwareMap, Telemetry)}
     * @param step the step to run
     * @param h the {@link HardwareMap} to be passed into the step
     * @param t the {@link Telemetry} to be passed into the step
     * @return the value returned by {@link #isFinished()}
     * @see #runStep(AutoStep, HardwareMap, Telemetry)
     */
    public static boolean runStepInLoop(AutoStep step, HardwareMap h, Telemetry t) {
        if (!step.isFinished()) {
            if (!step.initDone) {
                step.setTelemetry(t);
                step.setHardWareMap(h);
                step.init();
                step.initDone = true;
            } else {
                step.run();
            }
        }
        if (step.isFinished()) step.onFinish();
        return step.isFinished();
    }

    /**
     * Simply returns itself, allowing all AutoSteps to be used as a {@link Stepable}
     * @return itself, "this"
     */
    @Override
    public final AutoStep toAutoStep() {
        return this;
    }
}
