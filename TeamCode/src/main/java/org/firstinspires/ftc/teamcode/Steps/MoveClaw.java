package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

public class MoveClaw extends AutoStep {
    Claw c;
    double pos;
    public MoveClaw(double pos) {
        this.pos = pos;
    }

    @Override
    public void init() {
        c = new Claw(hardwareMap);
        c.setClaw(pos);
        setFinished(true);
    }

    @Override
    public void run() {

    }

    @Override
    protected void onFinish() {

    }
}
