package org.firstinspires.ftc.teamcode.LinearAuto;

import com.acmerobotics.roadrunner.drive.Drive;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.drive.SwerveDrive;
import com.acmerobotics.roadrunner.drive.TankDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Supplier;

public final class RoadRunner {
    private static SampleMecanumDrive driveTrain;
    public static AutoStep initialize(Pose2d startPos) {
        return new RunnableStep((h,t)-> {
            RoadRunner.driveTrain = new SampleMecanumDrive(h);
            RoadRunner.driveTrain.setPoseEstimate(startPos);
        });
    }
    public static SampleMecanumDrive driveTrain() {
        return driveTrain;
    }
    public static TrajectoryBuilder trajectoryBuilder(boolean reversed) {
        return driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(),reversed);
    }
    public static TrajectoryBuilder trajectoryBuilder() {
        return driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate());
    }
    public static TrajectoryBuilder trajectoryBuilder(double startingAngle) {
        return driveTrain().trajectoryBuilder(driveTrain.getPoseEstimate(),startingAngle);
    }
    public static TrajectorySequenceBuilder trajectorySequenceBuilder() {
        return driveTrain.trajectorySequenceBuilder(driveTrain.getPoseEstimate());
    }
    public static AutoStep followTrajectory(Supplier<Trajectory> t) {
        return new RunnableStep(()->driveTrain.followTrajectory(t.get()));
    }
    public static AutoStep followTrajectorySequence(Supplier<TrajectorySequence> t) {
        return  new RunnableStep(()->driveTrain.followTrajectorySequence(t.get()));
    }
    public static AutoStep rotate(double radians) {
        return new RunnableStep(()->driveTrain.turn(radians));
    }
}
