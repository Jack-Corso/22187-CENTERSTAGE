package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor m;
    double power = 0;
    public Intake(HardwareMap h) {
        m = h.dcMotor.get("intake");
        m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double power) {
        this.power = power;
        m.setPower(power);
    }
    public double getPower() {
        return power;
    }
}
