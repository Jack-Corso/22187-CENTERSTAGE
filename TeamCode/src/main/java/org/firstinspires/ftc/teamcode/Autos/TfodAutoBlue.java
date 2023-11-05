package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
@Autonomous(name="tfodAutoBlue")
public class TfodAutoBlue extends LinearAuto {
    static RunnableStep r;
    public TfodAutoBlue() {
        super(
//                new TfodBase(ReadTfod.BLUE)
                new TfodBase(ReadTfod.BLUE)
//                r = new RunnableStep(()->{
//                    if (ReadTfod.getResult() == 0) AutoStep.runStep(new Rotate(112), r.getHardwareMap(),r.getTelemetry());
//                    else if (ReadTfod.getResult() == 2) AutoStep.runStep(new Rotate(-75),r.getHardwareMap(),r.getTelemetry());
//                    AutoStep.runStep(new MoveForward(28,0.25),r.getHardwareMap(),r.getTelemetry()),
//                })
        );
    }
}
