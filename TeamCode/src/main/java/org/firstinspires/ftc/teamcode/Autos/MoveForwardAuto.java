package org.firstinspires.ftc.teamcode.Autos;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.LinearAuto.LinearAuto;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunSynchronously;
import org.firstinspires.ftc.teamcode.LinearAuto.RunWithTimeout;
import org.firstinspires.ftc.teamcode.LinearAuto.StepSeries;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.LinearAuto.WaitStep;
import org.firstinspires.ftc.teamcode.Odometry;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveForward;
import org.firstinspires.ftc.teamcode.Steps.ReadTfod;
import org.firstinspires.ftc.teamcode.Steps.Rotate;
import org.firstinspires.ftc.teamcode.Steps.RotateClaw;
import org.firstinspires.ftc.teamcode.Steps.RotateSlide;
import org.firstinspires.ftc.teamcode.Steps.Strafe;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

@Autonomous(name = "moveForward")
/**
 * Used for testing purposes.
 */
public class MoveForwardAuto extends LinearAuto {
    public MoveForwardAuto() {
        super(
                new RunSynchronously(
                        new Odometry.Run(),
                        new MoveForward(24,0.5)
                )
        );
    }
}
