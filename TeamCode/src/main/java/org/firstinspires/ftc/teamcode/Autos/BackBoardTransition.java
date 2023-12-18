package org.firstinspires.ftc.teamcode.Autos;
import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;

public class BackBoardTransition extends StepSeries {
    public BackBoardTransition() {
        super (
                new MoveForward(-20,0.45),
                new Rotate(-90),
                new MoveForward(60,0.25),
                new Rotate(15),
                new WaitStep(0.3)
        );
    }
}
