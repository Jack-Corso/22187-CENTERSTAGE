package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {
    DcMotor rotate;
    DcMotor slide;
    public static final int PICKUP = 1465;
    public static final int DROPOFF = 235;
    public static final int HOME = 0;
    public Slide(HardwareMap h) {
        rotate = h.dcMotor.get("rotateSlide");
        slide = h.dcMotor.get("slide");
    }
    public void setRotate(double speed) {
        rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotate.setPower(speed);
    }
    public void setSlide(double speed) {
        slide.setPower(speed);
    }
    public int getRotate() {
        return rotate.getCurrentPosition();
    }
    public int getSlide() {
        return slide.getCurrentPosition();
    }
    public void setRotatePos(int pos) {
        rotate.setTargetPosition(pos);
        rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (pos > rotate.getCurrentPosition()) {
            rotate.setPower(0.1);
        } else {
            rotate.setPower(-0.1);
        }
    }

}
