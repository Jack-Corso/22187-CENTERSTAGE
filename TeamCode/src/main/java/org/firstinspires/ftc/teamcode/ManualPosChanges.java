package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

@TeleOp(name="setPositions")
public class ManualPosChanges extends LinearOpMode {
    double speedDivider = 5;
    @Override
    public void runOpMode() {
        for (DcMotor motor : hardwareMap.getAll(DcMotor.class)) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        DriveTrain driveTrain = new DriveTrain(hardwareMap);
        Slide slide = new Slide(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        Launcher launcher = new Launcher(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            //increase speed if right trigger is down
            speedDivider = 2.5;
            if (gamepad1.right_trigger > 0) speedDivider = 1;
            //control slide
            if (gamepad2.right_trigger > 0) slide.setSlide(0.25);
            else if (gamepad2.left_trigger > 0) slide.setSlide(-0.25);
            else slide.setSlide(0);
            // control slide rotation
            if (gamepad2.y) slide.setRotate(0.05);
            else if (gamepad2.x) slide.setRotate(-0.05);
            else slide.setRotate(0);
            // control claw
            if (gamepad2.b) claw.setClaw(1);
            else if (gamepad2.a) claw.setClaw(0);
            // control claw rotation
            if (gamepad2.left_bumper) claw.setRotate(claw.getRotate()+0.01);
            else if (gamepad2.right_bumper) claw.setRotate(claw.getRotate()-0.01);
            // control airplane launcher rotation
            if (gamepad2.dpad_up) launcher.set(launcher.getStored()+0.1);
            else if (gamepad2.dpad_down) launcher.set(launcher.getStored()-0.1);
            // make the robot move with the controller
            double y = -gamepad1.left_stick_y / speedDivider; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1 / speedDivider; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x / speedDivider;
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
            telemetry.addData("clawRotate", claw.getStoredRotate());
            telemetry.addData("claw", claw.getClaw());
            telemetry.addData("Launcher", launcher.getStored());
            telemetry.update();
            //sets motor power
            driveTrain.setMotorSpeeds(frontLeftPower,backLeftPower,backRightPower,frontRightPower);
        }
    }
}