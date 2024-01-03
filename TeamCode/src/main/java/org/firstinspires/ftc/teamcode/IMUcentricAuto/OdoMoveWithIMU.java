package org.firstinspires.ftc.teamcode.IMUcentricAuto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;

public class OdoMoveWithIMU {
    double correction;


    SimplifyCodeIMU simplifyCode = new SimplifyCodeIMU();
    public void strafe(MyHardware myHardware, double rotations, double speed, boolean left, boolean align, Telemetry t){
        simplifyCode.MotorMode(myHardware, "run");
        if(align) {
            simplifyCode.align(myHardware);
        }
        simplifyCode.odometryreset(myHardware,true);
        double diameter = 1.88976;
        //from gobilda website
        double ticksPerRotation = 2000;
        //ticks/circumference
        double ticksPerInch = ticksPerRotation/(diameter*Math.PI);
        double data=ticksPerInch*rotations;
//        double data = 538*rotations;
//        if (inches) {
//            data = 46.2*rotations;//exact number is 45.36
//        }
        int value = (int)data;
        if (!left){
            data = 0-data;
        }
        if(left){
            myHardware.getFrontRight().setPower(speed);
            myHardware.getBackLeft().setPower(speed);
            myHardware.getFrontLeft().setPower(-speed);
            myHardware.getBackRight().setPower(-speed);
        } else {
            myHardware.getFrontRight().setPower(-speed);
            myHardware.getBackLeft().setPower(-speed);
            myHardware.getFrontLeft().setPower(speed);
            myHardware.getBackRight().setPower(speed);
        }
        ArrayList<Double> correctionList = new ArrayList<>();
        int i = 0;
        if (left) {
            while (simplifyCode.odoValue(myHardware, true) < data) {
                //determining if corrections should be made
                if (myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + myHardware.imuAngle == 0) {
                    correction = 0;
                } else {
                    correction = -myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + myHardware.imuAngle;
                }
                //adding robot specific offset
                correction = correction * 0.01;
                //applying corrections to motors
                    myHardware.getFrontRight().setPower(speed + correction);
                    myHardware.getBackLeft().setPower(speed - correction);
                    myHardware.getFrontLeft().setPower(-speed - correction);
                    myHardware.getBackRight().setPower(-speed + correction);
            }
        }else{
            while (simplifyCode.odoValue(myHardware, true) > data) {
                i++;
                t.addData("e", i);
                t.addData("E", myHardware.getBack().getCurrentPosition());
                    t.update();

            //determining if corrections should be made
            if (myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + myHardware.imuAngle == 0) {
                correction = 0;
            } else {
                correction = -myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + myHardware.imuAngle;
            }
            //adding robot specific offset
            correction = correction * 0.01;
            if (correction != 0) {
                correctionList.add(correction);
            }
            //applying corrections to motors
                myHardware.getFrontRight().setPower(speed - correction);
                myHardware.getBackLeft().setPower(speed + correction);
                myHardware.getFrontLeft().setPower(-speed + correction);
                myHardware.getBackRight().setPower(-speed - correction);
        }
            t.addData("e", i);
            t.addData("correction ", correctionList);
            t.update();
        }

        simplifyCode.setPower(myHardware,0,0);
//        simplifyCode.MotorMode(myHardware, "run");
    }

    public void move(MyHardware myHardware, double rotations, double speed, boolean back, boolean align){
        simplifyCode.MotorMode(myHardware, "run");
        if (align) {
            simplifyCode.align(myHardware);
        }

        simplifyCode.odometryreset(myHardware,true);
//        double data = 538*rotations;
//        if (inches) {
//            data = 46.2*rotations;//exact number is 45.36
//        }
        double diameter = 1.88976;
        //from gobilda website
        int ticksPerRotation = 2000;
        //ticks/circumference
        double ticksPerInch = ticksPerRotation/(diameter*Math.PI);
        double data=ticksPerInch*rotations;
        int value = (int)data;
        if (back){
            data=-data;
        }

        if (back) {
            simplifyCode.setPower(myHardware,-speed,-speed);
        } else {
            simplifyCode.setPower(myHardware,speed,speed);
        }
        if (back){
            while(simplifyCode.odoValue(myHardware, false)>data){
                //determining if corrections should be made
                if (myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)+myHardware.imuAngle == 0) {
                    correction = 0;
                }
                else {
                    correction = -myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)+myHardware.imuAngle;
                }
                //adding robot specific offset
                correction = correction * 0.01;
                //applying corrections to motors
                    simplifyCode.setPower(myHardware, speed+correction, speed-correction);
            }
        } else {
            while(simplifyCode.odoValue(myHardware, false)<data){
                //determining if corrections should be made
                if (myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)+myHardware.imuAngle == 0) {
                    correction = 0;
                }
                else {
                    correction = -myHardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)+myHardware.imuAngle;
                }
                //adding robot specific offset
                correction = correction * 0.01;
                //applying corrections to motors

                    simplifyCode.setPower(myHardware, speed - correction, speed + correction);

            }
        }

        simplifyCode.setPower(myHardware,0,0);
        simplifyCode.MotorMode(myHardware, "run");
    }
}
