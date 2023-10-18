package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Strafe;

@Autonomous(name = "moveForward")
public class MoveForwardAuto extends LinearAuto {
    int i = 5;
    public MoveForwardAuto() {
        super(
                //move, strafe, strafe, move
                new RunIf(true,
                        new MoveForward(12,0.5)
                ).elseIfDo(true,
                        new Strafe(12,0.5)
                ),
                new WaitStep(2.5),
                new RunIf(false,
                        new MoveForward(12,0.5)
                ).elseIfDo(true,
                        new Strafe(12,0.5)
                ),
                new WaitStep(2.5),
                new RunIf(false,
                        new MoveForward(12,0.5)
                ).elseDo(
                        new Strafe(12,0.5)
                ),
                new WaitStep(2.5),
                new RunIf(true,
                        new MoveForward(12,0.5)
                ).elseDo(
                        new Strafe(12,0.5)
                )//,
//                new MoveForward(20,1),
//                new WaitStep(2.5),
//                new MoveForward(20,0.5)
        );
    }
}
