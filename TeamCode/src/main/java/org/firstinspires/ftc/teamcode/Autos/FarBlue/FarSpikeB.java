package org.firstinspires.ftc.teamcode.Autos.FarBlue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
@Autonomous(name = "SpikeFarBlue",group = "FarBlue")
public class FarSpikeB extends LinearAuto {
    //finished
    public FarSpikeB() {
        super(
            new TfodBase(ReadTfod.BLUE,2)
        );
    }
}
