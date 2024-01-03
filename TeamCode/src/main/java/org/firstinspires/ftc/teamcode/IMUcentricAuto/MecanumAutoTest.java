package org.firstinspires.ftc.teamcode.IMUcentricAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autos.BackboardBase;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.Steps.AltReadTfod;
import org.firstinspires.ftc.teamcode.Steps.AprilTag;
import org.firstinspires.ftc.teamcode.Steps.MoveDropper;
import org.firstinspires.ftc.teamcode.subsystems.Dropper;

@Autonomous(name = "MecanumAutoIMUcentricTest", group = "Auto")
public class MecanumAutoTest extends LinearOpMode {
    MyHardware H = new MyHardware();
    RotateWithIMU R = new RotateWithIMU();
    MoveWithIMU M = new MoveWithIMU();
    AutoMergedFunctions F = new AutoMergedFunctions();
    SimplifyCodeIMU S = new SimplifyCodeIMU();
    int color=0;

//    public void linear(int rotations, double speed, boolean down) {
//        H.getLinear().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        telemetry.addData("GoalTicks", rotations);
//        H.getLinear().setTargetPosition(rotations);
//        H.getLinear().setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (down){
//            H.getLinear().setPower(-speed);
//        } else {
//            H.getLinear().setPower(speed);
//        }
//        while(H.getLinear().isBusy()){
//
//        }
//        H.getLinear().setPower(0);
//        H.getLinear().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }


    @Override
    public void runOpMode() {
//        AutoStep.runStep(new ReadTfod(),hardwareMap,telemetry);
        H.initialize(hardwareMap, telemetry);
        H.getImu().resetYaw();
        waitForStart();
        AutoStep.runStep(new BackboardBase(()->4), hardwareMap, telemetry);

    }
}
