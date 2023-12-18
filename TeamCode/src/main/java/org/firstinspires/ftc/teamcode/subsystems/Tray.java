package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Tray {
    Servo s;
    public static final double home = 0;
    public static final double drop = 1;
    public static final double hold = 0.5;
    public Tray(HardwareMap h) {
        s = h.get(Servo.class,"tray");
    }
    public void setPos(double pos) {
        s.setPosition(pos);
    }
}
