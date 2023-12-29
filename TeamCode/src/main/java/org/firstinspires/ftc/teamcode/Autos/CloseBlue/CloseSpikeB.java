package org.firstinspires.ftc.teamcode.Autos.CloseBlue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;

@Autonomous(name="SpikeCloseBlue",group = "CloseBlue")
public class CloseSpikeB extends LinearAuto {
    //finished!
    public CloseSpikeB() {
        super (
                new TfodBase(ReadTfod.BLUE,0)
        );
    }
}
