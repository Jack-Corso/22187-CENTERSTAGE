package org.firstinspires.ftc.teamcode.LinearAuto;

import java.util.Arrays;

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
