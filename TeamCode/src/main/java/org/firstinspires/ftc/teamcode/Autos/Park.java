package org.firstinspires.ftc.teamcode.Autos;

import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.Strafe;

import java.util.function.IntSupplier;

public class Park extends StepSeries {
    public Park(IntSupplier ID) {
        super(
                new RunIf(()->ID.getAsInt() == 1,
                        new Strafe(-24,0.5)
                ).elseIfDo(()->ID.getAsInt() == 2,
                        new Strafe(-28.5,0.5)
                ).elseIfDo(()->ID.getAsInt() == 3,
                        new Strafe(-35,0.5)
                ).elseIfDo(()->ID.getAsInt() == 4,
                        new Strafe(45,0.5)
                ).elseIfDo(()->ID.getAsInt() == 5,
                        new Strafe(35.5,0.5)
                ).elseIfDo(()->ID.getAsInt() == 6,
                        new Strafe(31,0.5)
                ),
                new MoveForward(10,0.5)
        );
    }
}
