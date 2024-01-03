package org.firstinspires.ftc.teamcode.IMUcentricAuto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class RotateWithIMU {

    boolean loopDone = false;
    SimplifyCodeIMU simplifyCode = new SimplifyCodeIMU();

    public void GoToAngle(MyHardware myHardware, Telemetry vTelemetry) {
        loopDone = false;
        YawPitchRollAngles orientation = myHardware.getImu().getRobotYawPitchRollAngles();
        double preAngle = orientation.getYaw(AngleUnit.DEGREES);
        if (orientation.getYaw(AngleUnit.DEGREES)<=myHardware.imuAngle) {
            simplifyCode.setPower(myHardware, -0.4, 0.4);
            int i=0;
            while (!loopDone) {
                i++;
                vTelemetry.addData("desired angle", myHardware.imuAngle);
                vTelemetry.addData("angle Pre", preAngle);
                vTelemetry.addData("1 First Loop i ", i);
                vTelemetry.addData("loop1R", orientation.getYaw(AngleUnit.DEGREES));
                vTelemetry.update();
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                if (orientation.getYaw(AngleUnit.DEGREES) >= myHardware.imuAngle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }
            }
            i=0;
            orientation = myHardware.getImu().getRobotYawPitchRollAngles();
            loopDone = false;
            simplifyCode.setPower(myHardware, 0.1, -0.1);
            while (!loopDone) {
                i++;
                vTelemetry.addData("desired angle", myHardware.imuAngle);
                vTelemetry.addData("angle Pre", preAngle);
                vTelemetry.addData("1 Second Loop i ", i);
                vTelemetry.addData("loop2R", orientation.getYaw(AngleUnit.DEGREES));
                vTelemetry.update();
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                vTelemetry.addData("angle", orientation.getYaw(AngleUnit.DEGREES));
                if (orientation.getYaw(AngleUnit.DEGREES) <= myHardware.imuAngle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }

            }
        }
        else if (orientation.getYaw(AngleUnit.DEGREES)>=myHardware.imuAngle) {
            simplifyCode.setPower(myHardware,0.4,-0.4);
            int i =0;
            while (!loopDone) {
                i++;
                vTelemetry.addData("desired angle", myHardware.imuAngle);
                vTelemetry.addData("angle Pre", preAngle);
                vTelemetry.addData("2 First Loop i  ", i);
                vTelemetry.addData("loop1L", orientation.getYaw(AngleUnit.DEGREES));
                vTelemetry.update();
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                vTelemetry.addData("angle", orientation.getYaw(AngleUnit.DEGREES));
                if (orientation.getYaw(AngleUnit.DEGREES) <= myHardware.imuAngle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }


            }
            orientation = myHardware.getImu().getRobotYawPitchRollAngles();

            loopDone = false;
            simplifyCode.setPower(myHardware, -0.1, 0.1);
            i=0;
            while (!loopDone) {
                i++;
                vTelemetry.addData("desired angle", myHardware.imuAngle);
                vTelemetry.addData("angle Pre", preAngle);
                vTelemetry.addData("2 Second Loop i ", i);
                vTelemetry.addData("I2 ", i);
                vTelemetry.addData("loop2L", orientation.getYaw(AngleUnit.DEGREES));
                vTelemetry.update();
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                if (orientation.getYaw(AngleUnit.DEGREES) >= myHardware.imuAngle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }

            }
        }
    }


    public void left(MyHardware myHardware, double angle, Telemetry vTelemetry) {
        myHardware.getImu().resetYaw();
        simplifyCode.setPower(myHardware, -0.5, 0.5);
        while (!loopDone) {
            YawPitchRollAngles orientation = myHardware.getImu().getRobotYawPitchRollAngles();
            vTelemetry.addData("angle", orientation.getYaw(AngleUnit.DEGREES));
            if (orientation.getYaw(AngleUnit.DEGREES) >= angle) {
                simplifyCode.setPower(myHardware, 0, 0);
                loopDone = true;
            }

        }
        YawPitchRollAngles orientation = myHardware.getImu().getRobotYawPitchRollAngles();

            loopDone = false;
            simplifyCode.setPower(myHardware, 0.1, -0.1);
            while (!loopDone) {
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                vTelemetry.addData("angle", orientation.getYaw(AngleUnit.DEGREES));
                if (orientation.getYaw(AngleUnit.DEGREES) <= angle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }

            }
            loopDone = false;
        }


    public void right(MyHardware myHardware, double angle, Telemetry vTelemetry){
        myHardware.getImu().resetYaw();
        loopDone = false;
        simplifyCode.setPower(myHardware,0.5,-0.5);
        while (!loopDone) {
            YawPitchRollAngles orientation = myHardware.getImu().getRobotYawPitchRollAngles();
            vTelemetry.addData("angle", orientation.getYaw(AngleUnit.DEGREES));
            if (orientation.getYaw(AngleUnit.DEGREES) <= -angle) {
                simplifyCode.setPower(myHardware, 0, 0);
                loopDone = true;
            }


        }
        YawPitchRollAngles orientation = myHardware.getImu().getRobotYawPitchRollAngles();

            loopDone = false;
            simplifyCode.setPower(myHardware, -0.1, 0.1);

            while (!loopDone) {
                orientation = myHardware.getImu().getRobotYawPitchRollAngles();
                if (orientation.getYaw(AngleUnit.DEGREES) >= -angle) {
                    simplifyCode.setPower(myHardware, 0, 0);
                    loopDone = true;
                }

            }
            loopDone = false;
        }
    }






