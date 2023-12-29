package org.firstinspires.ftc.teamcode.Autos.CloseRed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autos.CloseBlue.CloseSpikeB;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;

@Autonomous(name="SpikeCloseRed",group = "CloseRed")
public class CloseSpikeR extends LinearAuto {
    //finished!
    public CloseSpikeR() {
        super(
                new TfodBase(ReadTfod.RED,1)
        );
    }

}
