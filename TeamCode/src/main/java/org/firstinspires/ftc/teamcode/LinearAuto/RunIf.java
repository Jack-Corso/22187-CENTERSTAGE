package org.firstinspires.ftc.teamcode.LinearAuto;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.R;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/**
 *  AutoStep equivalent of if-elseif-else statement.
 *  elsif and else statements are represented by recursive methods, with a if-elseif-elseif-else statement looking like:
 *  <p>
 *     {@link #RunIf(BooleanSupplier, AutoStep) new RunIf()}.{@link #elseIfDo(BooleanSupplier, AutoStep) elseIfDo()}.{@link #elseIfDo(BooleanSupplier, AutoStep) elseIfDo()}.{@link #elseDo(AutoStep) elseDo()}
 *  </p>
 * @see #RunIf(BooleanSupplier, AutoStep)  RunIf()
 * @see #elseIfDo(BooleanSupplier, AutoStep) elseIfDo()
 * @see #elseDo(AutoStep) elseDo()
 */
public class RunIf extends AutoStep{
    BooleanSupplier condition;

    /**
     * The step to be run if {@link #condition}.{@link BooleanSupplier#getAsBoolean() getAsBoolean()}
     */
    AutoStep step;
    /**
     * An ArrayList containing a {@link RunIf} representation of all {@link #elseIfDo(BooleanSupplier, AutoStep) elseIfDo()} and {@link #elseDo(AutoStep) elseDo()}.
     * For information on how these a represented, see their descriptions.
     * <p></p>
     * ON {@link #init() INITIALIZATION}:
     * <p>
     *     If the {@link #condition 'initial' condition} is 'true' this is completely ignored,
     *     otherwise, this ArrayList is iterated through, and sets the {@link #step} and {@link #condition} to the first 'true' statement it finds.
     * </p>
     * @see #RunIf(BooleanSupplier, AutoStep) new RunIf()
     * @see #elseIfDo(BooleanSupplier, AutoStep) elseIfDo()
     * @see #elseDo(AutoStep) elseDo() 
     */
    ArrayList<RunIf> tree = new ArrayList<>();


    /**
     * If statement equivalent.
     * Does not add to the {@link #tree}, but instead sets 'initial' values.
     * If the initial condition is 'true' this step does not check the {@link #tree}, and simply keeps the initial values.
     * If the initial condition is 'false' this step then checks the {@link #tree}.
     * If the initial condition and all tree conditions are 'false', nothing is run
     * @param condition Determines if this if statement is run.
     *                  This value is a {@link BooleanSupplier} so that it can change after it has been passed in to the constructor.
     *                  Since {@link BooleanSupplier } is a {@link FunctionalInterface} it can be easily represented by a lambda expression
     * @param step The step to run if condition.{@link BooleanSupplier#getAsBoolean() getAsBoolean()} returns 'true'
     */
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
                    break;
                }

            }
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

    /**
     * Else-If statement equivalent.
     * Adds a {@link #RunIf(BooleanSupplier, AutoStep) new RunIf} with the given parameters to the {@link #tree}.
     * @param condition the condition given to the {@link #tree}.
     *                  see the {@link #RunIf(BooleanSupplier, AutoStep) constructor} for information on why this is a {@link BooleanSupplier}
     * @param step the step given to the {@link #tree}
     * @return returns itself, 'this' to allow method recursion
     */
    public RunIf elseIfDo(BooleanSupplier condition, AutoStep step) {
        tree.add(new RunIf(condition,step));
        return this;
    }

    /**
     * Else statement equivalent.
     * Adds a {@link #RunIf(BooleanSupplier, AutoStep) new RunIf} with the given step, using 'true' as the condition to the {@link #tree}
     * @param step the step given to the {@link #tree}
     * @return returns itself, 'this' to allow method recursion
     */
    public RunIf elseDo(AutoStep step) {
        tree.add(new RunIf(()->true,step));
        return this;
    }

    /**
     * returns the condition of this RunIf object
     * @return the {@link #condition 'initial' condition},
     * not any conditions from {@link #elseIfDo(BooleanSupplier, AutoStep) elseifDo()} or {@link #elseDo(AutoStep) elseDo()} calls
     */
    public BooleanSupplier getCondition() {
        return condition;
    }
}
