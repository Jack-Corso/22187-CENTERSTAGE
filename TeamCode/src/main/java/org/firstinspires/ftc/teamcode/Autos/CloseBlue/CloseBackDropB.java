package org.firstinspires.ftc.teamcode.Autos.CloseBlue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;

@Autonomous(name="BackdropCloseBlue",group = "CloseBlue")
public class CloseBackDropB extends LinearAuto {
    //finished!
    public CloseBackDropB() {
        super(
                new TfodBase(ReadTfod.BLUE,0),
                StartPositions.backBoardTransition(0,ReadTfod::getResult),
                new BackboardBase(()-> AprilTag.getIDFromDetect(ReadTfod.BLUE,ReadTfod.getResult()))
        );
    }
}
