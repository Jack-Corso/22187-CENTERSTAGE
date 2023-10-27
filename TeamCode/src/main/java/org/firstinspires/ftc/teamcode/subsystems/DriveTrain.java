package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    HardwareMap h;
    public DriveTrain(HardwareMap hardwareMap) {
        h = hardwareMap;
        frontRight = h.dcMotor.get("frontRight");
        backRight = h.dcMotor.get("backRight");
        backLeft = h.dcMotor.get("backLeft");
        frontLeft = h.dcMotor.get("frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setMotorSpeeds(double frSpeed,double brSpeed, double flSpeed, double blSpeed) {
        setFrontRight(frSpeed);
        setBackRight(brSpeed);
        setFrontLeft(flSpeed);
        setBackLeft(blSpeed);
    }
    public void setAll(double speed) {
        setMotorSpeeds(speed,speed,speed,speed);
    }
    public void setFrontRight(double speed) {
        frontRight.setPower(speed);
    }
    public void setBackRight(double speed) {
        backRight.setPower(speed);
    }
    public void setBackLeft(double speed) {
        backLeft.setPower(speed);
    }
    public void setFrontLeft(double speed) {
        frontLeft.setPower(speed);
    }
    public int getFrontRight() {
        return frontRight.getCurrentPosition();
    }
    public int getFrontLeft() {
        return frontLeft.getCurrentPosition();

    }public int getBackRight() {
        return backRight.getCurrentPosition();
    }
    public int getBackLeft() {
        return backLeft.getCurrentPosition();
    }



}
