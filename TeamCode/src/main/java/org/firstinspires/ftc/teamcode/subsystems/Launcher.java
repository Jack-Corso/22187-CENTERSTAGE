package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {
    Servo lServo;
    double storedPos = 0;
    public Launcher(HardwareMap h) {
        lServo = h.get(Servo.class, "launcher");
    }
    public void set(double pos) {
        lServo.setPosition(pos);
        storedPos = pos;
    }
    public double get() {
        return lServo.getPosition();
    }
    public double getStored() {
        return storedPos;
    }

}
