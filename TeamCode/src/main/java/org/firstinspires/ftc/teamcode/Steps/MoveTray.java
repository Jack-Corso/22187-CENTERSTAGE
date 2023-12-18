package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.Tray;

public class MoveTray extends AutoStep {
    double pos;
    public MoveTray(double pos) {
        this.pos = pos;
    }
    @Override
    public void init() {
        new Tray(hardwareMap).setPos(pos);
        setFinished(true);
    }

    @Override
    public void run() {

    }

    @Override
    protected void onFinish() {

    }
}
