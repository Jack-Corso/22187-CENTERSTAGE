package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;

import java.util.Arrays;

public class Odometry {
    public static final String leftName = "hang"; // port 0
    public static final String middleName = "intake"; // port 1
    public static final String rightName = "right"; // port 2

    //todo fix ticksPerInch
    public static final double rightY = 6;
    public static final double leftY = -6;
    public static final double backX = 2;
    //diameter in inches (48mm from gobilda website)
    public static final double diameter = 1.88976;
    //from gobilda website
    public static final int ticksPerRotation = 2000;
    //ticks/circumference
    public static final double ticksPerInch = ticksPerRotation/(diameter*Math.PI);
    public static final double ticksPerDegree = (((4*Math.PI)*ticksPerInch)/360);
    private static double[] pos = new double[]{0,0};
    private static double rotation = 0;
    private static boolean finish = false;
    public static double[] getPos() {
        return pos;
    }
    public static double[] getPosInches() {
        return new double[] {pos[0]/ticksPerInch,pos[1]/ticksPerInch};
    }
    public static double getRotationDegrees(){
        return rotation/ticksPerDegree;
    }
    public static double getRotation() {
        return rotation;
    }
    private static void set(double x,double y,double rotation) {
        pos = new double[]{x,y};
        Odometry.rotation = rotation;
    }
    public static class Run extends AutoStep {
        double x = 0;
        double y = 0;
        int i = 0;
        double currentRotation = 0;
        DcMotor right;
        DcMotor left;
        DcMotor back;
        @Override
        public void init() {
            right = hardwareMap.dcMotor.get(rightName);
            left = hardwareMap.dcMotor.get(leftName); //port 0
            back = hardwareMap.dcMotor.get(middleName);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rotation = 0;
            x = 0;
            y = 0;
            i = 0;
        }

        @Override
        public void run() {
            i++;
            //forward/backward movement
            double fwd = ((right.getCurrentPosition()* leftY) - (left.getCurrentPosition() * rightY))/
                    (leftY - rightY);
            //heading/rotation
            double theta = (left.getCurrentPosition()-right.getCurrentPosition())/
                    (leftY - rightY);
            //strafing movement
            double str = back.getCurrentPosition() - (backX *theta);
            double relX;
            double relY;
            if (theta != 0) {
                // radius's
                double r0 = fwd / theta;
                double r1 = str / theta;
                // relative positions (without rotation)
                relX = (r0 * Math.sin(theta)) - (r1 * (1 - Math.cos(theta)));
                relY = (r1 * Math.sin(theta)) + (r0 * (1 - Math.cos(theta)));
            } else {
                //linear math if no heading
                relX = fwd;
                relY = str;
            }
            //final positions
            x +=(relX*Math.cos(currentRotation))-(relY*Math.sin(currentRotation));
            y +=(relY*Math.cos(currentRotation))+ (relX*Math.sin(currentRotation));
            // give values to odometry
            currentRotation += theta;
            set(x,y,currentRotation);
            setFinished(finish);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            telemetry.addData("pos","(%.2f,%.2f)",x/ticksPerInch,y/ticksPerInch);
            telemetry.addData("pos (ticks)","(%.2f,%.2f)",pos[0],pos[1]);
            telemetry.addData("rotation",rotation);
            telemetry.addData("currentRotation",hardwareMap.get(IMU.class,"imu").getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.addData("degrees",getRotationDegrees());
            telemetry.addData("Encoders",
                          "\n   left: %d" +
                                "\n   right: %d" +
                                "\n   back: %d", left.getCurrentPosition(),right.getCurrentPosition(),back.getCurrentPosition());
            telemetry.addData("iterations",i);
            telemetry.addData("fwd",fwd);
            telemetry.addData("str",str);
            telemetry.addData("theta",theta);
            telemetry.addData("currentRotation (var)",currentRotation);
            telemetry.addData("","relX %f \nrelY %f",relX,relY);
            telemetry.addData("X Change","(%.2f*%.2f)-(%.2f*%.2f)",relX,Math.cos(currentRotation),relY,Math.sin(currentRotation));
            telemetry.addData("Y Change","(%.2f*%.2f)+(%.2f*%.2f)",relY,Math.cos(currentRotation),relX,Math.sin(currentRotation));
            telemetry.update();
        }

        @Override
        protected void onFinish() {

        }
    }
    public static class Stop extends RunnableStep {
        public Stop() {
            super(()->finish=true);
        }
    }
}
