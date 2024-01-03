package org.firstinspires.ftc.teamcode.Steps;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class AltReadTfod {
    public static final String RED = "red";
    public static final String BLUE = "blue";
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private final String TFOD_MODEL_FILE; //= "/sdcard/FIRST/tflitemodels/redElement.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    protected HardwareMap hardwareMap = null;
    protected Telemetry telemetry = null;

    private static final String[] LABELS = {
            "b",
            "r"
    };
    static int result = 0;
    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    private TfodProcessor tfod;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;


    public AltReadTfod(String type, HardwareMap hm, Telemetry t) {
        if (!type.equals(RED) && !type.equals(BLUE)) throw new IllegalArgumentException("Invalid type, use \"red\" or \"blue\", or the static variables");
        TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/" + type + "Element.tflite";
        this.hardwareMap = hm;
        this.telemetry = t;
    }

    public void init() {
        result = 0;
        initTfod();
        telemetry.addLine("Tfod initialized");
        telemetry.update();
    }

    public void execute(){
        init();
        run();
    }

    public void run() {
       telemetryTfod();
       telemetry.update();
    }

    protected void onFinish() {
        telemetry.addLine(String.valueOf(AltReadTfod.getResult()));
        telemetry.addLine(String.valueOf(getResult()));
        telemetry.update();
        visionPortal.close();
        tfod.shutdown();
    }
    public void resetVisionPortal(){
        visionPortal.close();
    }
    private void initTfod() {

        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()

                // With the following lines commented out, the default TfodProcessor Builder
                // will load the default model for the season. To define a custom model to load,
                // choose one of the following:
                //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
                //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
                //.setModelAssetName(TFOD_MODEL_ASSET)
                .setModelFileName(TFOD_MODEL_FILE)

                // The following default settings are available to un-comment and edit as needed to
                // set parameters for custom models.
                .setModelLabels(LABELS)
                //.setIsModelTensorFlow2(true)
                //.setIsModelQuantized(true)
                //.setModelInputSize(300)
                //.setModelAspectRatio(16.0 / 9.0)

                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder().enableLiveView(true);

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()
    private void telemetryTfod() {
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());
        telemetry.update();
        int i = 0;
        while(i< 300000){
            currentRecognitions = tfod.getRecognitions();
            telemetry.addData("i ", i);
            telemetry.update();
            i++;
        };
        telemetry.addData("# Objects Detected", currentRecognitions.size());
        telemetry.addData("i ", i);
        telemetry.update();

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
            if (x < 320) {
                AltReadTfod.setResult(1);
                break;
            }
            else if (!currentRecognitions.isEmpty())  {
                AltReadTfod.setResult(2);
                break;
            }
            //setFinished(true);
            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }   // end for() loop

    }   // end method telemetryTfod()
    public static int getResult() {
        return result;
    }
    private static void setResult(int val) {result = val;}
}
