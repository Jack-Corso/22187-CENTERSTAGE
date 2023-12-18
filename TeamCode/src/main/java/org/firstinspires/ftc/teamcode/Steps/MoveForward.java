package org.firstinspires.ftc.teamcode.Steps;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.Odometry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
@Config
public class MoveForward extends AutoStep {
    DriveTrain driveTrain;
    double distance;
    double speed;
    DcMotor left;
    DcMotor right;
    double mult = 1;
    public MoveForward(double inches, double speed) {
        distance = inches*Odometry.ticksPerInch;
        this.speed = speed;
    }
    @Override
    public void init() {
        left = hardwareMap.dcMotor.get(Odometry.leftName);
        right = hardwareMap.dcMotor.get(Odometry.rightName);
        driveTrain = new DriveTrain(hardwareMap);
        if (distance < 0) mult = -1;
        driveTrain.setAll(Math.abs(speed)*mult);
    }
    @Override
    public void run() {
        setFinished(Math.abs(distance) <= Math.abs((left.getCurrentPosition()+ right.getCurrentPosition())/2f));
    }
    @Override
    public void onFinish() {
        telemetry.addData("","left: %d | right: %d | average: %f",
                left.getCurrentPosition(),
                right.getCurrentPosition(),
                (left.getCurrentPosition()+right.getCurrentPosition())/2f);
        telemetry.update();
        driveTrain.setAll(0);
    }
}
