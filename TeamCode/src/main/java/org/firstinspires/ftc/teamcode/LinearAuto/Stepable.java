package org.firstinspires.ftc.teamcode.LinearAuto;
/**
 * Interface that allows classes which do not extend {@link AutoStep} to still be used in {@link LinearAuto} and {@link StepSeries}
 */
@FunctionalInterface
public interface Stepable {
    AutoStep toAutoStep();
}
