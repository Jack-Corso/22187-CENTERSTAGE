package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TeleOp")
public class DriverControl extends LinearOpMode {

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    double speedDivider = 5;
    @Override
    public void runOpMode() throws InterruptedException {

        for (DcMotor motor : hardwareMap.getAll(DcMotor.class)) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addLine("Finished motor init");
        telemetry.addData("frontRight:", frontRight.getPortNumber());
        telemetry.addData("backRight:", backRight.getPortNumber());
        telemetry.addData("backLeft:", backLeft.getPortNumber());
        telemetry.addData("frontLeft",frontLeft.getPortNumber());
        telemetry.update();
        // finished initializing
        frontRight.setPower(1);
        frontLeft.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(1);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            //increase speed if right trigger is down
            speedDivider = 2.5;
            if (gamepad1.right_trigger > 0) speedDivider = 2;
            // make the robot move with the controller
            double y = -gamepad1.left_stick_y / speedDivider; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1 / speedDivider; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x / speedDivider;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            //sets motor power
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
            frontRight.setPower(frontRightPower);
        }
    }
}
