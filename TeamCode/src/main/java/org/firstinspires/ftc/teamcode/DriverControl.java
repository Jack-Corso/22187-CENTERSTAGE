package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Hanger;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.PixelBox;
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
        //Claw claw = new Claw(hardwareMap);
        Launcher launcher = new Launcher(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Hanger hang = new Hanger(hardwareMap);
        Slide slide = new Slide(hardwareMap);
        PixelBox box = new PixelBox(hardwareMap);
        AprilTag.DriveTo driveTo;
        boolean atGoal = true;
        //rotateArm.setTargetPos(0);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            //increase speed if right trigger is down
            speedDivider = 1;
            if (gamepad1.right_trigger > 0) speedDivider = 0.000001;
            // control intake
            intake.setPower((int)gamepad2.right_trigger - (int) gamepad2.left_trigger);

            // control hanging
            if (gamepad2.b) hang.setPower(1);
            else if (gamepad2.a) hang.setPower(-1);
            else hang.setPower(0);

            // control slide
            if (gamepad2.y) slide.setSlide(1);
            else if (gamepad2.x) slide.setSlide(-1);
            else slide.setSlide(0);

            // control outtake box door
            if (gamepad2.left_bumper) box.setDoor(PixelBox.doorPositions.open);
            else if (gamepad2.right_bumper) box.setDoor(PixelBox.doorPositions.close);

            // control outtake box rotation
            if (gamepad2.dpad_left) box.setRotate(Claw.CLOSE);
            else if (gamepad2.dpad_right) box.setRotate(Claw.OPEN);
            if (gamepad2.dpad_up && gamepad2.left_stick_button && gamepad2.right_stick_button) launcher.set(1);

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
            //telemetry.addData("Slide Rotation", slide.getRotate());
            telemetry.addData("Slide",slide.getSlide());
            //telemetry.addData("Slide motorSpeed",rotateArm.motorSpeed(slide.getRotate()));
            telemetry.update();
            //sets motor power
            driveTrain.setMotorSpeeds(frontRightPower,backRightPower,frontLeftPower,backLeftPower);
//            if (rotateArm.motorSpeed(slide.getRotate()) > 1) {
//                slide.setRotate(1);
//            } else if (rotateArm.motorSpeed(slide.getRotate()) < -1) {
//                slide.setRotate(-1);
//            } else {
//                slide.setRotate(rotateArm.motorSpeed(slide.getRotate()));
//            }

        }
    }
}
