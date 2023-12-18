package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
//todo convert to new slide
public class Slide {
    DcMotor slide;
    public Slide(HardwareMap h) {
        slide = h.dcMotor.get("slide");
    }
    public void setSlide(double speed) {
        slide.setPower(speed);
    }
    public int getSlide() {
        return slide.getCurrentPosition();
    }
}
