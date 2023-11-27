package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;

public class Rotate extends AutoStep {
    IMU imu;
    DriveTrain driveTrain;
    int degrees;
    double prevDegrees;
    PIDController pidController = new PIDController(0.02,0.00004,0.1);

    /**
     *
     * @param degrees DO NOT PUT THIS HIGHER THAN 180!!!, because the motor goes from 180 -> -180, the PID will break (and probably the robot too)
     */
    public Rotate(int degrees) {
        // uses negative value so positive = right
        this.degrees = -degrees;

    }
    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");
        driveTrain = new DriveTrain(hardwareMap);
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP)));
        imu.resetYaw();
        pidController.setTargetPos(degrees);
        setFinished(degrees == (int) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
    }

    @Override
    public void run() {
        double targetSpeed = pidController.motorSpeed((int) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        driveTrain.setMotorSpeeds(targetSpeed,targetSpeed,-targetSpeed,-targetSpeed);
        telemetry.addData("Rotation: ",imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("targetSpeed", targetSpeed);
        pidController.toTelemetry(telemetry);
        telemetry.update();
        setFinished(pidController.isFinished());
    }

    @Override
    protected void onFinish() {
        driveTrain.setAll(0);
    }
    private int convertToPositive360(int degrees) {
        if (degrees < 0) return degrees+361;
        return degrees;
    }
    private int convertToNegative360(int degrees) {
        if (degrees > 0) return degrees-361;
        return degrees;
    }
}
