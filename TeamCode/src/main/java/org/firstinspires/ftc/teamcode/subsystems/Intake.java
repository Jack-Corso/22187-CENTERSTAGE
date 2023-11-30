package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor m;
    int power = 0;
    public Intake(HardwareMap h) {
        m = h.dcMotor.get("intake");
        m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * NOTE: motor power CANT be controlled precisely, when setting power,
     * the motor will go to full speed in the specified direction,
     * to avoid confusion the power has been made an int to reflect the lack of control
     * @param power the power from -1 -> 1
     */
    public void setPower(int power) {
        this.power = power;
        m.setPower(power);
    }
    public int getPower() {
        return power;
    }
}
