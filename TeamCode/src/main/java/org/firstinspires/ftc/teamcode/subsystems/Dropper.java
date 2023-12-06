package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Dropper {
    Servo servo;
    double storedPos = 0;
    public static final double drop = 0.5;
    public static final double clamp = 1;
    public Dropper(HardwareMap h) {
        servo = h.get(Servo.class, "dropper");
    }
    public void set(double pos) {
        servo.setPosition(pos);
        storedPos = pos;
    }
    public double get() {
        return servo.getPosition();
    }
    public double getStored() {
        return storedPos;
    }
}
