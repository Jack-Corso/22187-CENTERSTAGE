package org.firstinspires.ftc.teamcode.Autos.CloseRed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.Autos.CloseBlue.CloseBackDropB;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
//@Disabled //(unfinished, just remove this to test)
@Autonomous(name="BackdropCloseRed",group = "CloseRed")
public class CloseBackdropR extends LinearAuto {
    //unfinished
    public CloseBackdropR() {
        super(
                new TfodBase(ReadTfod.RED,1),
                StartPositions.backBoardTransition(1,ReadTfod::getResult), //TODO backboard transition needed (look in the method)
                new BackboardBase(()-> AprilTag.getIDFromDetect(ReadTfod.RED,ReadTfod.getResult()))
        );
    }
}
