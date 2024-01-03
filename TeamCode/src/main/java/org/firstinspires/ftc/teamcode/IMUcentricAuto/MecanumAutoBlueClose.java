package org.firstinspires.ftc.teamcode.IMUcentricAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AltDriveToAprilTag;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.Steps.AltReadTfod;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoArm;
import org.firstinspires.ftc.teamcode.Steps.MoveAutoClaw;
import org.firstinspires.ftc.teamcode.Steps.MoveDropper;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;
import org.firstinspires.ftc.teamcode.subsystems.Dropper;

@Autonomous(name = "MecanumAutoBlueClose", group = "Auto")
public class MecanumAutoBlueClose extends LinearOpMode {
    MyHardware H = new MyHardware();
    RotateWithIMU R = new RotateWithIMU();
    MoveWithIMU M = new MoveWithIMU();
    AutoMergedFunctions F = new AutoMergedFunctions();
    SimplifyCodeIMU S = new SimplifyCodeIMU();
    AltDriveToAprilTag A = new AltDriveToAprilTag();
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
        AltReadTfod rtf = new AltReadTfod("blue", hardwareMap, telemetry);
        H.initialize(hardwareMap, telemetry);
        H.getImu().resetYaw();
        AutoStep.runStep(new MoveDropper(Dropper.clamp), hardwareMap, telemetry);
        AutoStep.runStep(new MoveAutoClaw(MoveAutoClaw.RightConstants.CLOSE), hardwareMap, telemetry);
        waitForStart();
        rtf.execute();
        int result = rtf.getResult();
        rtf.resetVisionPortal();
            if (result == 0){
                M.strafe(H, 2, 0.25, true, true, true, telemetry);
            }

            M.move(H, 18, 0.25, false, true, false);
            sleep(1000);
            if(result == 0){
                H.imuAngle=-90;
                R.GoToAngle(H,telemetry);
                M.move(H,5,0.25,true,true,true);
                AutoStep.runStep(new MoveDropper(Dropper.drop), hardwareMap, telemetry);
                sleep(1000);//drop
                M.move(H, 9, 0.25, true, true, true);
                H.imuAngle=90;
                R.GoToAngle(H, telemetry);
            } else if (result ==1){
                M.move(H, 6, 0.25, false, true, true);
                M.strafe(H, 2, 0.25, false, true, true, telemetry);
                AutoStep.runStep(new MoveDropper(Dropper.drop), hardwareMap, telemetry);
                sleep(2000);//drop
                M.move(H, 14, 0.25, true, true, true);
                sleep(1000);
                H.imuAngle=90;
                R.GoToAngle(H,telemetry);
                M.move(H, 20, 0.25, false, true, true);
                M.strafe(H, 8, 0.25, false, true, true, telemetry);
            } else {
                H.imuAngle=-90;
                R.GoToAngle(H,telemetry);
                M.move(H, 6, 0.25, false, true, true);
                AutoStep.runStep(new MoveDropper(Dropper.drop), hardwareMap, telemetry);
                sleep(1000);//drop
                M.move(H, 26, 0.25, true, true, true);
                H.imuAngle=90;
                R.GoToAngle(H, telemetry);
            }
            telemetry.addData("looking for tag", rtf.getResult()+1);
            A.goToAprilTag(rtf.getResult()+1, hardwareMap, telemetry, H);
            //sleep(10000);
            telemetry.addData("done", "complete");
            S.setPower(H, 0,0);
            sleep(1000);
            if (result==2) {
                M.strafe(H, 2, 0.25, false, true, true,telemetry);
                M.move(H, 7, 0.25, false, true, true);
                sleep(1000);
                AutoStep.runStep(new MoveAutoArm(AutoArm.ArmPresets.dropoff), hardwareMap, telemetry);
                sleep(1000);
                AutoStep.runStep(new MoveAutoClaw(MoveAutoClaw.RightConstants.OPEN_FULL), hardwareMap, telemetry);
                sleep(10000);
            } else if (result==1) {
                M.strafe(H, 2, 0.25, false, true, true,telemetry);
                M.move(H, 7, 0.25, false, true, true);
                sleep(1000);
                AutoStep.runStep(new MoveAutoArm(AutoArm.ArmPresets.dropoff), hardwareMap, telemetry);
                sleep(1000);
                AutoStep.runStep(new MoveAutoClaw(MoveAutoClaw.RightConstants.OPEN_FULL), hardwareMap, telemetry);
                sleep(10000);
            } else {
                M.strafe(H, 2, 0.25, false, true, true,telemetry);
                M.move(H, 7, 0.25, false, true, true);
                sleep(1000);
                AutoStep.runStep(new MoveAutoArm(AutoArm.ArmPresets.dropoff), hardwareMap, telemetry);
                sleep(1000);
                AutoStep.runStep(new MoveAutoClaw(MoveAutoClaw.RightConstants.OPEN_FULL), hardwareMap, telemetry);
                sleep(10000);
            }

    }
}
