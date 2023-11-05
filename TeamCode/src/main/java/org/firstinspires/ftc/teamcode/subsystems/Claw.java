package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo rotate;
    Servo claw;
    public static final double PICKUP = 0.25;
    public static final double DROPOFF = 0;
    public static final double HOME = 0.5;
    public static final double OPEN = 0;
    public static final double CLOSE = 0.5;

    double storedRotation; //it breaks when getPosition() is called too often (solution)
    public Claw(HardwareMap h) {
        rotate = h.servo.get("rotateClaw");
        claw = h.servo.get("claw");
    }
    public void setRotate(double pos) {
        rotate.setPosition(pos);
        storedRotation = pos;
    }
    public void setClaw(double pos) {
        claw.setPosition(pos);
    }
    public double getRotate() {return rotate.getPosition();}
    public double getStoredRotate() {return storedRotation;}
    public double getClaw() {return claw.getPosition();}
}
