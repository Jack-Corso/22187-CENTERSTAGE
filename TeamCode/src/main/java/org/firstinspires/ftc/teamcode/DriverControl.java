package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

@TeleOp(name = "TeleOp")
public class DriverControl extends LinearOpMode {
    double speedDivider = 5;

    @Override
    public void runOpMode() {
        for (DcMotor motor : hardwareMap.getAll(DcMotor.class)) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            // motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        DriveTrain driveTrain = new DriveTrain(hardwareMap);
        Slide slide = new Slide(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        Launcher launcher = new Launcher(hardwareMap);
        PIDController rotateArm = new PIDController(0.004,0,0.001);
        AprilTag.DriveTo driveTo;
        boolean atGoal = true;
        //rotateArm.setTargetPos(0);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            //increase speed if right trigger is down
            speedDivider = 1;
            if (gamepad1.right_trigger > 0) speedDivider = 0.000001;
            //control slide
            if (gamepad2.y) slide.setRotate(1);
            else if (gamepad2.x) slide.setRotate(-1);
            else slide.setRotate(0);
            slide.setSlide(gamepad2.right_trigger - gamepad2.left_trigger);
            if (gamepad2.dpad_up && gamepad2.left_stick_button && gamepad2.right_stick_button) launcher.set(1);
            if (gamepad2.a) claw.setRotate(1);
            else if (gamepad2.b) claw.setRotate(Claw.DROPOFF);
            // control claw rotation
            // make the robot move with the controller
            double y = -gamepad1.left_stick_y / speedDivider; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1 / speedDivider; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x / speedDivider;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            telemetry.addData("FrontLeft", driveTrain.getFrontLeft());
            telemetry.addData("BackLeft", driveTrain.getBackLeft());
            telemetry.addData("FrontRight", driveTrain.getFrontRight());
            telemetry.addData("BackRight", driveTrain.getBackRight());
            telemetry.addData("Slide Rotation", slide.getRotate());
            telemetry.addData("Slide",slide.getSlide());
            telemetry.addData("Slide motorSpeed",rotateArm.motorSpeed(slide.getRotate()));
            telemetry.addData("Error", rotateArm.getError());
            telemetry.addData("Integral", rotateArm.getIntegral());
            telemetry.addData("Derivative", rotateArm.getDerivative());
            telemetry.update();
            //sets motor power
            driveTrain.setMotorSpeeds(frontRightPower,backRightPower,frontLeftPower,backLeftPower);

        }
    }
}
