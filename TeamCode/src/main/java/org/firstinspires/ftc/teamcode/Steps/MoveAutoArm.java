package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;


public class MoveAutoArm extends AutoStep {
    AutoArm arm;
    double pos;
    public MoveAutoArm(double pos) {
        this.pos = pos;
    }
    @Override
    public void init() {
        arm = new AutoArm(hardwareMap);
        arm.setArm(pos);
        setFinished(true);
    }

    @Override
    public void run() {

    }

    @Override
    protected void onFinish() {

    }
}
