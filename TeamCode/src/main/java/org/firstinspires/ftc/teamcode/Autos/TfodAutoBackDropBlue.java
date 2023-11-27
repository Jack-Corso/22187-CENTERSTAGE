package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

@Autonomous(name="tfodAutoBlueBackdrop")
public class TfodAutoBackDropBlue extends LinearAuto {
    public TfodAutoBackDropBlue() {
        super(
                new InitStep(new MoveClaw(Claw.CLOSE)),
                new TfodBase(ReadTfod.BLUE),
                new RunIf(()->ReadTfod.getResult() == 2,
                        new StepSeries(
                                new MoveForward(2,-0.5),
                                new Rotate(-90),
                                new Rotate(-90),
                                new MoveForward(5,0.5))
                ).elseIfDo(()->ReadTfod.getResult() == 1,
                        new StepSeries(
                                new MoveForward(4,-0.25),
                                new Rotate(-90),
                                new MoveForward(10,0.25),
                                new Strafe(6,0.25)
                        )
                ).elseDo(new StepSeries(
                        new Rotate(-10),
                        new Strafe(14,0.25),
                        new MoveForward(17,0.25),
                        new Strafe(14,-0.25)
                        )),
                new AprilTag.Initialize(),
                new BackboardBase(()->AprilTag.getIDFromDetect(ReadTfod.BLUE,ReadTfod.getResult()))

        );
    }
}
