package org.firstinspires.ftc.teamcode.Autos;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RaceSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateClaw;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

import java.util.function.IntSupplier;

public class BackboardBase extends StepSeries {
    public BackboardBase(IntSupplier ID) {
        super(
                //todo GO TO 1 AND STRAFE UNTIL DETECT
                //new RunWithTimeout(new AprilTag.DriveTo(ID,20),5),
                //new ToTelemetry("at 10!",false),
                //new RaceSynchronously(new Strafe(4,-0.2), new AprilTag.WaitUntilDetect((ID))),
                new RunWithTimeout(new AprilTag.DriveTo(ID,1),9),//todo INCREASE!!!!!!!!!!!!!!!!!!
                //new RaceSynchronously(new Strafe(24,0.2),new AprilTag.WaitUntilDetect(ID)),
                new MoveForward(6,0.25),
                //new RunWithTimeout(new AprilTag.DriveTo(ID,0.5),4),
                //new RaceSynchronously(new Strafe(999,-0.1),new AprilTag.WaitUntilDetect(ID)),
                //new RunWithTimeout(new AprilTag.DriveTo(ID,1),3),
                new ToTelemetry("at 2!", false),
                //new MoveForward(6,0.5),
                new WaitStep(0.1),
                new RotateClaw(Claw.DROPOFF),
                new MoveClaw(Claw.OPEN),
                new WaitStep(1),
                new MoveForward(5,-0.5)
        );
    }
}
