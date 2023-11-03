package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

/**
 * NOTE: finishes immediately, no matter the position of the claw. This is because of the lack of encoders the servo,
 * making it impossible to determine if it is finished
 */
public class RotateClaw extends AutoStep {
    Claw c;
    double pos;
    public RotateClaw(double pos) {
        this.pos = pos;
    }
    @Override
    public void init() {
        c = new Claw(hardwareMap);
        c.setRotate(pos);
        setFinished(true);
    }

    @Override
    public void run() {
    }

    @Override
    protected void onFinish() {

    }
}
