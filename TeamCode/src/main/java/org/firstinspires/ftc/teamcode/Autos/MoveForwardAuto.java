package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateClaw;
import org.firstinspires.ftc.teamcode.Steps.RotateSlide;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

@Autonomous(name = "moveForward")
/**
 * Used for testing purposes.
 */
public class MoveForwardAuto extends LinearAuto {
    int i = 5;
    public MoveForwardAuto() {
        super(
                new Rotate(90),
                new WaitStep(3),
                new Rotate(-90),
                new WaitStep(3),
                new Rotate(180)
        );
    }
}
