package org.firstinspires.ftc.teamcode.Autos;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.MoveClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateArm;
import org.firstinspires.ftc.teamcode.Steps.RotateSlide;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

public class TfodBase extends StepSeries {
   // static ReadTfod readTfod;
//    public TfodBase(String color) {
//        super(
//                new InitStep(new MoveClaw(Claw.CLOSE)),
//                new RunWithTimeout(readTfod = new ReadTfod(color),4),
    public TfodBase(ReadTfod readTfod) {
        super(
                new InitStep(new MoveClaw(Claw.CLOSE)),
                new RunWithTimeout(readTfod,4),
                new WaitStep(3),
                new RunWithTimeout(new ToTelemetry("result: " + ReadTfod.getResult(),true),3),
                new Strafe(4,-0.5),
                new MoveForward(26,0.5),
                new RunIf(ReadTfod.getResult() == 0, new StepSeries(
                        new Rotate(-70),
                        new MoveForward(3,-0.5),
                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
                )).elseIfDo(ReadTfod.getResult() == 2, new StepSeries(
                        new Rotate(70),
                        new MoveForward(3,-0.5),
                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
                )).elseDo(new StepSeries(
                        new MoveForward(20,0.25),
                        new MoveForward(20,-0.25),
                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
                )),
                new RotateArm(RotateArm.Position.PICKUP),
                new MoveClaw(Claw.OPEN),
                new WaitStep(0.75),
                new RotateArm(RotateArm.Position.HOME)
        );


    }
}
