package org.firstinspires.ftc.teamcode.Autos;

import android.accounts.AbstractAccountAuthenticator;

import org.firstinspires.ftc.teamcode.LinearAuto.RaceSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.Strafe;

public class AprilTagBase extends StepSeries {
    public AprilTagBase() {
        super(
                new BackboardBase(()->4)
        );
    }
}
