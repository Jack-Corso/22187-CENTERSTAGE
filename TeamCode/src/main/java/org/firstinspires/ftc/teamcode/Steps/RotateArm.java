package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.RunSynchronously;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

import java.util.HashMap;

/**
 * runs both the {@link RotateClaw} and {@link RotateSlide} synchronously based on input
 */
public class RotateArm extends RunSynchronously {
    public enum Position {
        PICKUP,
        DROPOFF,
        HOME
    }
    public RotateArm(Position pos) {
        super (
                new RotateClaw(getClawFromEnum(pos)),
                new RotateSlide(getSlideFromEnum(pos))
        );
    }
    private static double getClawFromEnum(Position pos) {
        if (pos == Position.PICKUP) return Claw.PICKUP;
        else if (pos == Position.DROPOFF) return Claw.DROPOFF;
        else return Claw.HOME;
    }
    private static int getSlideFromEnum(Position pos) {
        if (pos == Position.PICKUP) return Slide.PICKUP;
        else if (pos == Position.DROPOFF) return Slide.DROPOFF;
        else return Slide.HOME;
    }
}
