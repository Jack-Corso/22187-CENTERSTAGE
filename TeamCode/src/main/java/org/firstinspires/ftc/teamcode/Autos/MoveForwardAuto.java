package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.Rotate;

@Autonomous(name = "moveForward")
/**
 * Used for testing purposes.
 */
public class MoveForwardAuto extends LinearAuto {
    public MoveForwardAuto() {
        super(
                new InitStep(new MoveForward(24,0.5)),
                new MoveForward(24,0.25),
                new Rotate(90),
                new MoveForward(24,0.25),
                new WaitStep(5),
                new MoveForward(24,-0.5)
        );
    }
}
