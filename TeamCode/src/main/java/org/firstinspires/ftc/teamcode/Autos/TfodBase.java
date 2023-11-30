package org.firstinspires.ftc.teamcode.Autos;

import androidx.annotation.RequiresPermission;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
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

public class  TfodBase extends StepSeries {
   // static ReadTfod readTfod;
//    public TfodBase(String color) {
//        super(
//                new InitStep(new MoveClaw(Claw.CLOSE)),
//                new RunWithTimeout(readTfod = new ReadTfod(color),4),
    static RunnableStep r;
    public TfodBase(String color) {
        super(
                new InitStep(new MoveClaw(Claw.CLOSE)),
                new RunWithTimeout(new ReadTfod(color),4),
                new Strafe(4,-0.5),
                new MoveForward(26,0.5),
                r = new RunnableStep(()-> {
                    if (ReadTfod.getResult() == 0) {
                        AutoStep.runStep(new StepSeries(
                                new Rotate(-112),
                                new MoveForward(2,-0.5),
                                new ToTelemetry("Result: " + ReadTfod.getResult(),false)
                        ), r.getHardwareMap(),r.getTelemetry());
                    } else if(ReadTfod.getResult() == 1) {
                        AutoStep.runStep(new StepSeries(
                                new MoveForward(20,0.25),
                                new MoveForward(19,-0.25),
                                new ToTelemetry("Result: " + ReadTfod.getResult(),false)),r.getHardwareMap(),r.getTelemetry() );
                    } else {
                        AutoStep.runStep(new StepSeries(
                                new Rotate(75),
                                new MoveForward(2,-0.5),
                                new ToTelemetry("Result: " + ReadTfod.getResult(),false)
                        ),r.getHardwareMap(),r.getTelemetry());
                    }
                }),
//                new RunIf(ReadTfod.getResult() == 0, new StepSeries(
//                        new Rotate(-70),
//                        new MoveForward(3,-0.5),
//                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
//                )).elseIfDo(ReadTfod.getResult() == 2, new StepSeries(
//                        new Rotate(70),
//                        new MoveForward(3,-0.5),
//                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
//                )).elseDo(new StepSeries(
//                        new MoveForward(20,0.25),
//                        new MoveForward(20,-0.25),
//                        new ToTelemetry("Result: " + ReadTfod.getResult(),false)
//                )),
                new RotateArm(RotateArm.Position.PICKUP),
                new MoveClaw(Claw.OPEN),
                new WaitStep(0.75),
                new RotateArm(RotateArm.Position.HOME)

        );


    }
}
