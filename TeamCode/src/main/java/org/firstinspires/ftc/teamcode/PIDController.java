package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PIDController {
    double Kp;
    double Ki;
    double Kd;
    int error = 0;
    int lastError;
    int pos;
    double integral;
    double derivative;
    boolean finished = false;
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
        finished = Math.abs(out) < 0.1;
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

    /**
     * prints error, integral, and derivative to telemetry
     */
    public void toTelemetry(Telemetry t) {
        t.addData("Error", getError());
        t.addData("Integral", getIntegral());
        t.addData("Derivative",getDerivative());
    }
    public boolean isFinished() {
        return finished;
    }
}
