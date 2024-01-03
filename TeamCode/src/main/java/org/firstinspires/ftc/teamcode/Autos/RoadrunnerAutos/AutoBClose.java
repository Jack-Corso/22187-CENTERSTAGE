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
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Steps.AltReadTfod;
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

import java.util.Vector;

@Config
@Autonomous(name = "AutoBClose")
public class AutoBClose extends LinearOpMode {

    public static double TweakVal1 = 15;
    public static double TweakVal2 = 0;
    public static double TweakVal3 = 0;
    public static double TweakVal4 = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(StartPositions.startPositions[0]);
        Trajectory tr1= null;
        Trajectory tr2 = null;

        Trajectory preSpike = drive.trajectoryBuilder(StartPositions.startPositions[0])
                .lineToConstantHeading(new Vector2d(-12, -36))
                .build();

        Trajectory leftSpike = drive.trajectoryBuilder(preSpike.end())
                .strafeTo(StartPositions.spikePositions[0][0])
//                        SampleMecanumDrive.getVelocityConstraint(TweakVal1, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                //            .splineToConstantHeading(new Vector2d(48, 12), 0)
                .build();


        Trajectory middleSpike = drive.trajectoryBuilder(preSpike.end())
                .strafeTo(StartPositions.spikePositions[0][1])
//                        SampleMecanumDrive.getVelocityConstraint(TweakVal1, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                //            .splineToConstantHeading(new Vector2d(48, 12), 0)
                .build();


        Trajectory rightSpike = drive.trajectoryBuilder(preSpike.end())
                .strafeTo(StartPositions.spikeBlueCloseRight)
//                        SampleMecanumDrive.getVelocityConstraint(TweakVal1, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                //            .splineToConstantHeading(new Vector2d(48, 12), 0)
                .build();


        //    AutoStep.runStep(new ReadTfod("BLUE"),hardwareMap,telemetry);

//        RoadRunner.followTrajectory(SpikeMid);
//ReadTfod.getResult()
//        if(ReadTfod.getResult()==1){}
        AltReadTfod rtf = new AltReadTfod("blue", hardwareMap, telemetry);
        waitForStart(); //INIT
        rtf.execute();
        telemetry.addData("Result ", rtf.getResult());
        telemetry.update();
        AutoStep.runStep(new MoveDropper(Dropper.clamp), hardwareMap, telemetry);
//        AutoStep.runStep(new ReadTfod("blue"), hardwareMap, telemetry);
        drive.followTrajectory(preSpike);
        sleep(1000);
        if (rtf.getResult() == 0) {
            drive.followTrajectory(leftSpike);
            tr1 = drive.trajectoryBuilder(leftSpike.end())
                    .lineToConstantHeading(new Vector2d(-12, -36))
                    .build();
            tr2 = drive.trajectoryBuilder(tr1.end())
                    .lineToLinearHeading(new Pose2d(-36, -48, Math.toRadians(180)))
                    .build();
        } else if (rtf.getResult() == 1) {
            drive.followTrajectory(middleSpike);
            tr1 = drive.trajectoryBuilder(middleSpike.end())
                    .lineToConstantHeading(new Vector2d(-12, -36))
                    .build();
            tr2 = drive.trajectoryBuilder(tr1.end())
                    .lineToLinearHeading(new Pose2d(-36, -48, Math.toRadians(180)))
                    .build();
        } else {
            drive.followTrajectory(rightSpike);
            tr1 = drive.trajectoryBuilder(rightSpike.end())
                    .lineToConstantHeading(new Vector2d(-12, -36))
                    .build();
            tr2 = drive.trajectoryBuilder(tr1.end())
                    .lineToLinearHeading(new Pose2d(-36, -48, Math.toRadians(180)))
                    .build();
        }
        AutoStep.runStep(new MoveDropper(Dropper.drop), hardwareMap, telemetry);

//        AutoStep.runStep(new MoveAutoArm(AutoArm.ArmPresets.dropoff),hardwareMap,telemetry);
        sleep(1000);
        drive.followTrajectory(tr1);
        sleep(1000);
        drive.followTrajectory(tr2);
    }
}