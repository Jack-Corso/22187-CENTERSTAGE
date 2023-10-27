package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {
    double Kp;
    double Ki;
    double Kd;
    int error = 0;
    int lastError;
    int pos;
    double integral;
    double derivative;
    ElapsedTime timer = new ElapsedTime();
    public PIDController(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }
    public void setTargetPos(int pos) {
        timer.reset();
        this.pos = pos;
        lastError = 0;
        integral = 0;
    }
    public double motorSpeed(int currentPos) {
        double out;
        error = pos - currentPos;
        integral += (error * timer.seconds());
        derivative = (error - lastError) / timer.seconds();
        out = (Kp * error) + (Ki * integral) + (Kd * derivative);
        lastError = error;
        return out;
    }
    public double getError() {
        return error;
    }
    public double getIntegral() {
        return integral;
    }
    public double getDerivative() {
        return derivative;
    }
}
