package org.firstinspires.ftc.teamcode.LinearAuto;

import androidx.annotation.NonNull;

/**
 * Interface that allows classes which do not extend {@link AutoStep} to still be used in {@link LinearAuto} and {@link StepSeries}
 */
@FunctionalInterface
public interface Stepable {

    /**
     * Returns a non-null {@link AutoStep} that relates to/represents the implementing class
     * <p>
     * NOTE: Implementing classes MUST return a non-null value, as all constructors that accept this object expect it to return a non-null value
     * <p/>
     * @return the {@link AutoStep} that relates to/represents the implementing class.
     */
    AutoStep toAutoStep();
    static AutoStep[] toAutoStepArray(Stepable[] array) {
        AutoStep[] stepArray = new AutoStep[array.length];
        for (int i = 0; i < array.length; i++) {
            stepArray[i] = array[i].toAutoStep();
        }
        return stepArray;
    }
}
