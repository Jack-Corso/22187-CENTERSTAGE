package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
@Autonomous(name="tfodAuto")
public class TfodAuto extends LinearAuto {
    public TfodAuto() {
        super(
            new RunWithTimeout(new ReadTfod(),4),
            new MoveForward(20,0.5),

            new RunIf(ReadTfod.getResult() == 0, new StepSeries(
                    new Strafe(20,-0.5),
                    new ToTelemetry("Result: 0",false)
            )).elseIfDo(ReadTfod.getResult() == 2, new StepSeries(
                    new Strafe(20,0.5),
                    new ToTelemetry("Result: 2",false)
            ))
        );
    }
}
