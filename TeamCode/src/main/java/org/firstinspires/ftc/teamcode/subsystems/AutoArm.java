package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoArm {
    Servo left;
    Servo right;
    Servo arm;
    public AutoArm(HardwareMap h) {
        left = h.servo.get("leftClaw");
        right = h.servo.get("rightClaw");
        arm = h.servo.get("armServo");
    }
    public void setLeft(double pos) {
        left.setPosition(pos);
    }
    public void setRight(double pos) {
        right.setPosition(pos);
    }
    public void setArm(double pos) {
        arm.setPosition(pos);
    }
    public double getLeft() {
        return left.getPosition();
    }
    public double getRight() {
        return right.getPosition();
    }
    public double getArm() {
        return arm.getPosition();
    }
    public static final class LeftPresets {
        public static final double open = 0.5;
        public static final double close = 0;
        public static final double fullOpen = 1;
    }
    public static final class RightPresets {
        public static final double open = 0.5;
        public static final double close = 1;
        public static final double fullOpen = 0;
    }
    public static final class ArmPresets {
        public static final double pickup = 1;
        public static final double inside = 0;
        public static final double dropoff = 0.6;
    }
}
