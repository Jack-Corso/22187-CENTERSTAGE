package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.InitStep;
import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RaceSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Odometry;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;

@Autonomous(name = "moveForward")
/**
 * Used for testing purposes.
 */
public class MoveForwardAuto extends LinearAuto {
    public MoveForwardAuto() {
        super(
                new TfodBase(ReadTfod.BLUE),
                // rotate so robot facing forward for BackBoardTransition
                new RunIf(()->ReadTfod.getResult() == 0,
                        new Rotate(90)
                ).elseIfDo(()-> ReadTfod.getResult() == 2,
                        new StepSeries(
                        new MoveForward(1,0.25),
                        new Rotate(-90),
                        new MoveForward(-2,0.5)
                        )
                ),
                        //.elseDo(
                       // new MoveForward(-0.5,0.5)
                //),
               // new Rotate(90)
 //               new GrabFromStack(),
               new BackBoardTransition(),
                new BackboardBase(()-> AprilTag.getIDFromDetect(ReadTfod.BLUE,ReadTfod.getResult()))
        );
    }
}
