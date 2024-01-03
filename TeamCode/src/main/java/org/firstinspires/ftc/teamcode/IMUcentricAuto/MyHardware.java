package org.firstinspires.ftc.teamcode.IMUcentricAuto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MyHardware {

    IMU imu;
    public static final String leftName = "hang"; // port 0
    public static final String middleName = "intake"; // port 1
    public static final String rightName = "right"; // port 2
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor m;
    private DcMotor right;
    private DcMotor left;
    private DcMotor back;
    Servo leftClaw;
    Servo rightClaw;
    Servo armServo;
    Servo dropper;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    double imuAngle = 0;
    public void initialize(HardwareMap hardwareMap, Telemetry vTelemetry){
        telemetry = vTelemetry;
        imu = hardwareMap.get(IMU.class, "imu");
        imu.resetYaw();
        m = hardwareMap.get(DcMotor.class, "intake");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        right = hardwareMap.get(DcMotor.class, rightName);
        left = hardwareMap.get(DcMotor.class, leftName);
        back = hardwareMap.get(DcMotor.class, middleName);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        armServo = hardwareMap.get(Servo.class, "armServo");
        dropper = hardwareMap.get(Servo.class, "dropper");
    }


    public void setLeft(double pos) {
        leftClaw.setPosition(pos);
    }
    public void setRight(double pos) {
        rightClaw.setPosition(pos);
    }
    public void setArm(double pos) {
        armServo.setPosition(pos);
    }
    public void setDropper(double pos) {dropper.setPosition(pos);}
   // LeftPresets
        //open = 0.5;
        //close = 0;
        //fullOpen = 1;
  //  }
    // RightPresets {
        // open = 0.5;
       //  close = 1;
    //  fullOpen = 0;
    //}
//     ArmPresets {
        // pickup = 1;
        // inside = 0;
        // dropoff = 0.6;
    //}
    //drop = 0.5;
    //clamp = 0;

    public IMU getImu() {
        return imu;
    }

    public void setImu(IMU imu) {
        this.imu = imu;
    }

    public DcMotor getRight() {
        return right;
    }
    public void setRight(DcMotor right) {
        this.right = right;
    }

    public DcMotor getLeft() {
        return left;
    }
    public void setLeft(DcMotor left) {
        this.left = left;
    }

    public DcMotor getBack() {
        return back;
    }
    public void setBack(DcMotor back) {
        this.back = back;
    }

    public DcMotor getFrontLeft() {
        return frontLeft;
    }
    public void setFrontLeft(DcMotor frontLeft) {
        this.frontLeft = frontLeft;
    }

    public DcMotor getBackLeft() {
        return backLeft;
    }
    public void setBackLeft(DcMotor backLeft) {
        this.backLeft = backLeft;
    }

    public DcMotor getFrontRight() {
        return frontRight;
    }
    public void setFrontRight(DcMotor frontRight) {
        this.frontRight = frontRight;
    }

    public DcMotor getBackRight() {
        return backRight;
    }
    public void setBackRight(DcMotor backRight) {
        this.backRight = backRight;
    }

    public DcMotor getIntake() {
        return m;
    }
    public void setIntake(DcMotor m) {
        this.m = m;
    }


//    public Servo getClaw() {
//        return claw;
//    }
//
//    public void setClaw(Servo claw) {
//        this.claw = claw;
//    }

}
