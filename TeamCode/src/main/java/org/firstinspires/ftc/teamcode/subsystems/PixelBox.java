package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PixelBox {
    Servo rotate;
    Servo door;
    public PixelBox(HardwareMap h) {
        rotate = h.servo.get("rotateBox");
        door = h.servo.get("door");
    }
    public void setRotate(double pos) {
        rotate.setPosition(pos);
    }
    public void setDoor(double pos) {
        door.setPosition(pos);
    }

    public static final class rotatePositions {
        public static final double intakePos = 0.01;
        public static final double outtakePos = 0.35;
    }
    public static final class doorPositions {
        public static final double open = 0.5;
        public static final double close = 0;
    }
}
