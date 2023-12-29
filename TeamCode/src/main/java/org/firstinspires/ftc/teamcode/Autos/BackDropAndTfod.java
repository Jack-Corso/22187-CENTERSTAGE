package org.firstinspires.ftc.teamcode.Autos;

import androidx.annotation.RequiresPermission;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;

import java.util.Objects;
@Deprecated
public class BackDropAndTfod extends StepSeries {
    public BackDropAndTfod(String color) {
        super (
            new InitStep(new MoveAutoClaw(MoveAutoClaw.Constants.CLOSE_BOTH)),
                new TfodBase(color,0),
                // transition from tfod -> backdrop
                //BLUE
                new RunIf(()->color.equals(ReadTfod.BLUE),
                    new StepSeries(
                            new RunIf(()->ReadTfod.getResult() == 2,
                                new StepSeries(
                                        new Rotate(-90)
                                )
                            ).elseIfDo(()->ReadTfod.getResult() == 1,
                                    new Rotate(-90)
                            ).elseDo(
                                    new Strafe(-5,0.5)
                            )
                    )
                //RED
                ).elseDo(
                        new RunIf(()->ReadTfod.getResult() == 0,
                                new StepSeries(
                                        new Rotate(90)
                                )
                        ).elseIfDo(()->ReadTfod.getResult() == 1,
                                new StepSeries(
                                        new Rotate(90),
                                        new MoveForward(18,0.5)
                                )
                        ).elseDo(
                                new StepSeries(
                                new Rotate(120)
                                //new Strafe(-15,0.5)
                                )
                        )
                ),
                new Strafe(-19,0.5),
                new BackboardBase(()->AprilTag.getIDFromDetect(color,ReadTfod.getResult())),
                new Park(()->AprilTag.getIDFromDetect(color,ReadTfod.getResult()))
        );
    }
}
