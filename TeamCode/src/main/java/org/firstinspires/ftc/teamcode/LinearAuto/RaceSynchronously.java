package org.firstinspires.ftc.teamcode.LinearAuto;

import java.util.Arrays;

/**
 * Runs the given steps using RunSynchronously, instead ending the program when ANY of the steps finish, running all unfinished steps {@link AutoStep#onFinish()} method
 * <p>
 *     for more information on how steps are run, see {@link RunSynchronously}
 * </p>
 * @see RunSynchronously
 */
public class RaceSynchronously extends RunSynchronously {
    public RaceSynchronously(AutoStep... steps) {
        super(steps);
    }

    @Override
    public void run() {
        super.run();
        setFinished(Arrays.stream(steps).anyMatch(AutoStep::isFinished));
    }
}
