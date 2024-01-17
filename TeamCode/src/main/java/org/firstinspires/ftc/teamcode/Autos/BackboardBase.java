package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RaceSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateClaw;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.PixelBox;

import java.util.function.IntSupplier;

public class BackboardBase extends StepSeries {
    static RunnableStep r;
    public BackboardBase(IntSupplier ID) {
        super(
                new InitStep(new MoveAutoClaw(MoveAutoClaw.RightConstants.CLOSE)),
                new AprilTag.Initialize(),
                new RunWithTimeout(new AprilTag.DriveTo(ID,10),3),
                new RunWithTimeout(new MoveForward(15,0.25),2),
                new MoveAutoArm(AutoArm.ArmPresets.dropoff),
                new WaitStep(0.5),
                new MoveAutoClaw(MoveAutoClaw.RightConstants.OPEN_FULL),
                new WaitStep(0.75),
                new MoveAutoClaw(MoveAutoClaw.Constants.CLOSE_BOTH),
                new MoveAutoArm(AutoArm.ArmPresets.inside),
                //new WaitStep(0.5),
                new MoveForward(-3,0.25),
                new AprilTag.Close()
//                //todo GO TO 1 AND STRAFE UNTIL DETECT
//                //new RunWithTimeout(new AprilTag.DriveTo(ID,20),5),
//                //new ToTelemetry("at 10!",false),
//                //new RaceSynchronously(new Strafe(4,-0.2), new AprilTag.WaitUntilDetect((ID))),
//                new RunWithTimeout(new AprilTag.DriveTo(ID,1),9),//todo INCREASE!!!!!!!!!!!!!!!!!!
//                //new RaceSynchronously(new Strafe(24,0.2),new AprilTag.WaitUntilDetect(ID)),
//                new MoveForward(6,0.25),
//                //new RunWithTimeout(new AprilTag.DriveTo(ID,0.5),4),
//                //new RaceSynchronously(new Strafe(999,-0.1),new AprilTag.WaitUntilDetect(ID)),
//                //new RunWithTimeout(new AprilTag.DriveTo(ID,1),3),
//                new ToTelemetry(()->"at 2!", false),
//                //new MoveForward(6,0.5),
//                new WaitStep(0.1),
//                r = new RunnableStep(()->new PixelBox(r.getHardwareMap()).setRotate(Claw.OPEN)),
//                new WaitStep(1),
//                new MoveForward(5,-0.5)
        );
    }
}
