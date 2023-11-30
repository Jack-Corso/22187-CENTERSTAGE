package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hanger {
    int power;
    DcMotor m;
    public Hanger(HardwareMap h) {
        m = h.dcMotor.get("hang");
    }
    public void setPower(int i) {
        power = i;
        m.setPower(i);
    }
    public int getPower() {
        return power;
    }

}
