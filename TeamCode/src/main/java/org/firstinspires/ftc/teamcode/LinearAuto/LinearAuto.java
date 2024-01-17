package org.firstinspires.ftc.teamcode.LinearAuto;

import android.os.Environment;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManagerImpl;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeServices;
import org.firstinspires.ftc.teamcode.Autos.TfodAutoBluePark;
import org.threeten.bp.Instant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.IntFunction;

/**
 * Runs the given {@link AutoStep}s in order.
 * This is done by creating a new {@link StepSeries}
 * with the passed in steps and {@link AutoStep#runStep(AutoStep, HardwareMap, Telemetry) running} it.
 * This Class extends {@link  LinearOpMode},
 * and is responsible for passing in the {@link AutoStep#hardwareMap HardwareMap} and {@link AutoStep#telemetry Telemetry} instances for {@link AutoStep}
 * @see StepSeries
 */
public abstract class LinearAuto extends LinearOpMode implements Stepable{
    StepSeries stepSeries;
    public LinearAuto(Stepable... steps) {
        stepSeries = new StepSeries(steps);
    }
    @Override
    public final AutoStep toAutoStep() {
        return stepSeries;
    }
    @Override
    public void runOpMode() {

        for (AutoStep step : stepSeries.steps) {
            if (step.runOnInit) AutoStep.initializeStep(step,hardwareMap,telemetry);
        }

        waitForStart();
        try {
            AutoStep.runStep(stepSeries, hardwareMap, telemetry);
        } catch (Exception e) {
            logException(e);
            throw e;
        }
        requestOpModeStop();
    }
    public void logException(Exception e) {
        try {
            File log = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/ExceptionLogs/"
                            + e.getClass().getSimpleName() + ":"+ System.currentTimeMillis() + ".txt"
            );
            log.createNewFile();
            PrintStream logPrintStream = new PrintStream(log);
            logPrintStream.println(Arrays.toString(e.getStackTrace()));
            logPrintStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
