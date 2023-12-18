package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;
@Autonomous(name="grabStack")
public class GrabFromStack extends LinearAuto {
    public GrabFromStack(){
        super(
                //new Rotate(90),
                new MoveAutoArm(AutoArm.ArmPresets.pickup),
                new MoveAutoClaw(MoveAutoClaw.LeftConstants.OPEN_FULL),
                new MoveForward(18,0.5),
                new RunWithTimeout(new MoveForward(1,0.2),2),
                new MoveAutoArm(AutoArm.ArmPresets.pickup),
                new MoveAutoClaw(MoveAutoClaw.LeftConstants.CLOSE),
                new WaitStep(0.3),
                new MoveAutoArm(AutoArm.ArmPresets.inside),
                new MoveForward(-17,0.5),
                new Rotate(-90)
        );
    }
}
