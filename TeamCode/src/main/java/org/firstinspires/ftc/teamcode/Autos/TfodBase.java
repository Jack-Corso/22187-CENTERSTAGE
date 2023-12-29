 package org.firstinspires.ftc.teamcode.Autos;

import androidx.annotation.RequiresPermission;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RoadRunner;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveDropper;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.MoveTray;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateArm;
import org.firstinspires.ftc.teamcode.Steps.RotateBox;
import org.firstinspires.ftc.teamcode.Steps.RotateClaw;
import org.firstinspires.ftc.teamcode.Steps.RotateSlide;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Dropper;
import org.firstinspires.ftc.teamcode.subsystems.PixelBox;
import org.firstinspires.ftc.teamcode.subsystems.Tray;

 public class TfodBase extends StepSeries {
   // static ReadTfod readTfod;
//    public TfodBase(String color) {
//        super(
//                new InitStep(new MoveClaw(Claw.CLOSE)),
//

    public TfodBase(String color, int loc) {
        super(
                new RunWithTimeout(new ReadTfod(color),4),
                new InitStep(new MoveDropper(Dropper.clamp)),
                RoadRunner.initialize(StartPositions.startPositions[loc]),
                RoadRunner.followTrajectory(()->
                        RoadRunner.trajectoryBuilder()
                                //.forward(20)
                                .splineTo(StartPositions.spikePositions[loc][3],RoadRunner.driveTrain().getPoseEstimate().getHeading())
                                .splineTo(StartPositions.spikePositions[loc][ReadTfod.getResult()],
                                        RoadRunner.driveTrain().getPoseEstimate().getHeading()+StartPositions.spikeRotation[ReadTfod.getResult()]
                                )
                                .build()
                ),
                new WaitStep(0.5),
                new MoveDropper(Dropper.drop)
        );
        //OLD CODE!!!
//        new InitStep(Rotate.resetIMU()),
//                new InitStep(new RunnableStep((h,t)-> {
//                        t.addData("imu", h.get(IMU.class,"imu").getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
//                        t.update();
//                })),
//                //new InitStep(new RotateBox(Claw.CLOSE)),
//                new InitStep(new MoveDropper(Dropper.clamp)),
//                new RunWithTimeout(new ReadTfod(color),4),
//                new MoveForward(2,0.25),
//                new Strafe(-1.5,0.25),
//                new Rotate(0),
//                new MoveForward(23,0.5),
//                new RunIf(()->ReadTfod.getResult() == 0,
//                            new StepSeries(
//                                    new Rotate(-90),
//                                    new MoveForward(8,0.25),
//                                    new Strafe(5,0.5)
//                            )
//                ).elseIfDo(()->ReadTfod.getResult() == 1,
//                        new StepSeries(
//                                new Strafe(-0.5,0.5),
//                                new MoveForward(7,0.25)
//                        )
//                ).elseDo(
//                        new StepSeries(
//                                new Rotate(90),
//                                new MoveForward(6.5,0.25)
//                        )
//                ),
//                new WaitStep(0.25),
//                new MoveDropper(Dropper.drop),
//                new WaitStep(0.5),
//                new MoveForward(-9,0.25)
//              //  new Strafe(100,0.25)


    }
}
