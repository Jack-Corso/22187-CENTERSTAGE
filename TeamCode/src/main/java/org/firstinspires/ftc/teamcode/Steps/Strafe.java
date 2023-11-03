package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.Util;

public class Strafe extends AutoStep {
    double distance;
    double speed;
    double traveledDist = 0;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    public Strafe(double inches, double speed) {
        distance = (inches*46.2) * 0.85; //values from strafing at 0.5 speed. Should avoid speeds over 0.5 for consistency
        this.speed = speed;
    }
    @Override
    public void init() {
        Util.initMotors(hardwareMap);
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
    }

    @Override
    public void run() {
        setFinished(distance <= Math.abs(frontLeft.getCurrentPosition()));
    }

    @Override
    protected void onFinish() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
