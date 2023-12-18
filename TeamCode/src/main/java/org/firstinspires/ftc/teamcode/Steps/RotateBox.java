package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.PixelBox;


public class RotateBox extends AutoStep {
    PixelBox pBox;
    double targetPos;
    public RotateBox(double pos) {
        targetPos = pos;
    }
    @Override
    public void init() {
        pBox = new PixelBox(hardwareMap);
        pBox.setRotate(targetPos);
        setFinished(true);
    }

    @Override
    public void run() {

    }

    @Override
    protected void onFinish() {

    }
}
