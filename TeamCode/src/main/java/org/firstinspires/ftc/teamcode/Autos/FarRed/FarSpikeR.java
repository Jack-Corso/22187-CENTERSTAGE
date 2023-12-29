package org.firstinspires.ftc.teamcode.Autos.FarRed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
@Autonomous(name = "SpikeFarRed",group = "FarRed")
public class FarSpikeR extends LinearAuto {
    //finished
    public FarSpikeR() {
        super(
            new TfodBase(ReadTfod.RED,3)
        );
    }
}
