package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo rotate;
    Servo claw;
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
