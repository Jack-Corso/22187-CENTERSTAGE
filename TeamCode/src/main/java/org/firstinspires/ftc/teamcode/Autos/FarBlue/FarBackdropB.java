package org.firstinspires.ftc.teamcode.Autos.FarBlue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.Autos.TfodBase;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.StartPositions;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
//@Disabled //(unfinished, just remove this to test)
@Autonomous(name="BackdropFarBlue",group = "FarBlue")
public class FarBackdropB extends LinearAuto {
    //unfinished
    public FarBackdropB() {
        super(
                new TfodBase(ReadTfod.BLUE,2),
                StartPositions.backBoardTransition(2,ReadTfod::getResult), //TODO backboard transition needed (look in the method)
                new BackboardBase(()-> AprilTag.getIDFromDetect(ReadTfod.BLUE,ReadTfod.getResult()))
        );
    }
}
