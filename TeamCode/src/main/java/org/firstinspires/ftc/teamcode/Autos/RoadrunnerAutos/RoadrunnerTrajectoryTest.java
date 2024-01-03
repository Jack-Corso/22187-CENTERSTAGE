package org.firstinspires.ftc.teamcode.Autos.RoadrunnerAutos;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.VectorEnabledTintResources;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RaceSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RoadRunner;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Odometry;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.RoadRunner.Trajectorys;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveDropper;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;
import org.firstinspires.ftc.teamcode.subsystems.Dropper;
@Config
@Autonomous(name = "RoadrunnerTrajectoryTest")
public class RoadrunnerTrajectoryTest extends LinearOpMode {

    public static double TweakVal1=0;
    public static double TweakVal2=0;
    public static double TweakVal3=0;
    public static double TweakVal4=0;



    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
                Trajectory test = drive.trajectoryBuilder(new Pose2d())
                        .strafeTo(new Vector2d(24, 0))
                        .splineToConstantHeading(new Vector2d(36, 12), 0)
        //            .splineToConstantHeading(new Vector2d(48, 12), 0)

                        .build();


                Trajectory reset = drive.trajectoryBuilder(test.end())
                        .strafeTo(new Vector2d(0, 0))
                        .build();
    //    AutoStep.runStep(new ReadTfod("BLUE"),hardwareMap,telemetry);

//        RoadRunner.followTrajectory(SpikeMid);
//ReadTfod.getResult(
        drive.setPoseEstimate(StartPositions.startPositions[0]);
        waitForStart();
        AutoStep.runStep(new ReadTfod("BLUE"),hardwareMap,telemetry);
        drive.followTrajectory(test);
        AutoStep.runStep(new MoveAutoArm(AutoArm.ArmPresets.dropoff),hardwareMap,telemetry);
        drive.followTrajectory(reset);
    }

}
