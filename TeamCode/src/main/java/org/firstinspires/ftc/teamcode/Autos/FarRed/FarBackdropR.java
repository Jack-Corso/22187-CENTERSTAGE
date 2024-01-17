package org.firstinspires.ftc.teamcode.Autos.FarRed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
//@Disabled //(unfinished, just remove this to test)
@Autonomous(name="BackdropFarRed",group = "FarRed")
public class FarBackdropR extends LinearAuto {
    //unfinished
    public FarBackdropR() {
        super(
                new TfodBase(ReadTfod.RED,3),
                StartPositions.backBoardTransition(3,ReadTfod::getResult), //TODO backboard transition needed (look in the method)
                new BackboardBase(()-> AprilTag.getIDFromDetect(ReadTfod.RED,ReadTfod.getResult()))
        );
    }
}
