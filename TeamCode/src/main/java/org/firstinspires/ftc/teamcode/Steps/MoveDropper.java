package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.Dropper;

public class MoveDropper extends AutoStep {
    double pos;
    public MoveDropper(double pos) {
        this.pos = pos;
    }
    Dropper dropper;
    @Override
    public void init() {
        dropper = new Dropper(hardwareMap);
        dropper.set(pos);
        setFinished(true);
    }

    @Override
    public void run() {
        setFinished(true);
    }

    @Override
    protected void onFinish() {

    }
}
