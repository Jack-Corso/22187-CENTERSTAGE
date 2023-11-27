package org.firstinspires.ftc.teamcode.Steps;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.Objects;
import java.util.function.IntSupplier;

public class AprilTag {
    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private static AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private static VisionPortal visionPortal;
    public static int getIDFromDetect(String color, int pos) {
        int add = 1;
        if (color.equals(ReadTfod.RED)) add = 4;
        return pos + add;
    }

    public static class Initialize extends AutoStep {
        private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
        public Initialize() {
            //runOnInit = true;
        }
        @Override
        public void init() {
            initAprilTag();
            telemetry.addLine("Initialized AprilTag");
            telemetry.update();
            setFinished(true);
        }

        @Override
        public void run() {

        }

        @Override
        protected void onFinish() {

        }
        private void initAprilTag() {

            // Create the AprilTag processor.
            aprilTag = new AprilTagProcessor.Builder()

                    // The following default settings are available to un-comment and edit as needed.
                    //.setDrawAxes(false)
                    //.setDrawCubeProjection(false)
                    //.setDrawTagOutline(true)
                    //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                    //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                    //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                    // == CAMERA CALIBRATION ==
                    // If you do not manually specify calibration parameters, the SDK will attempt
                    // to load a predefined calibration for your camera.
                    //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                    // ... these parameters are fx, fy, cx, cy.

                    .build();

            // Adjust Image Decimation to trade-off detection-range for detection-rate.
            // eg: Some typical detection data using a Logitech C920 WebCam
            // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
            // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
            // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
            // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
            // Note: Decimation can be changed on-the-fly to adapt during a match.
            //aprilTag.setDecimation(3);

            // Create the vision portal by using a builder.
            VisionPortal.Builder builder = new VisionPortal.Builder();

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
            builder.addProcessor(aprilTag);

            // Build the Vision Portal, using the above settings.
            visionPortal = builder.build();

            // Disable or re-enable the aprilTag processor at any time.
            //visionPortal.setProcessorEnabled(aprilTag, true);

        }   // end method initAprilTag()
    }

    public static class Close extends RunnableStep {
        public Close() {
            super (() -> visionPortal.close());
        }
    }

    public static  class DriveTo extends AutoStep {
        private final IntSupplier ID;
        private final double desiredDistance;
        private DriveTrain driveTrain;
        //  Modified from gain -> PID
        private final PIDController SPEED_PID = new PIDController(0.01,0.000025,1.5);
        private final PIDController STRAFE_PID = new PIDController(0.005,0.00005,2);
        private final PIDController TURN_PID = new PIDController(0.01,0,0.25);

        private final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value TODO (adjust for your robot)
        private final double MAX_AUTO_STRAFE= 0.5;   //  Clip the approach speed to this max value TODO (adjust for your robot)
        private final double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value TODO (adjust for your robot)

        public DriveTo(int ID, double desiredDistance) {
            this(()->ID,desiredDistance);
        }
        public DriveTo(IntSupplier ID, double desiredDistance) {
            this.ID = ID;
            this.desiredDistance = desiredDistance;
        }
        @Override
        public void init() {
            driveTrain = new DriveTrain(hardwareMap);
        }

        @Override
        public void run() {
            AprilTagDetection tag = getDesiredTag(ID.getAsInt());
            telemetry.update();
            if (tag != null) {
                // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
                double  rangeError      = (tag.ftcPose.range - desiredDistance);
                double  headingError    = tag.ftcPose.bearing;
                double  yawError        = tag.ftcPose.yaw;

                // Use the speed and turn "gains" to calculate how we want the robot to move.
//                drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
//                turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
//                strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
                moveRobot(-SPEED_PID.motorSpeed(rangeError),-STRAFE_PID.motorSpeed(-yawError),-TURN_PID.motorSpeed(headingError));
                telemetry.addData("Speed Error", rangeError);
                telemetry.addData("Speed Output", -SPEED_PID.motorSpeed(rangeError));
                telemetry.addData("Strafe Error", yawError);
                telemetry.addData("Strafe Output", -STRAFE_PID.motorSpeed(-yawError));
                telemetry.addData("Turn Error", headingError);
                telemetry.addData("Turn Output", -TURN_PID.motorSpeed(headingError));
                telemetry.update();
                setFinished(SPEED_PID.isFinished() && STRAFE_PID.isFinished() && TURN_PID.isFinished());
            } else {
                moveRobot(0,0,0);
            }
        }

        @Override
        protected void onFinish() {
            driveTrain.setAll(0);
        }

        private void moveRobot(double x, double y, double yaw) {
            // Calculate wheel powers.
            double leftFrontPower    =  x -y -yaw;
            double rightFrontPower   =  x +y +yaw;
            double leftBackPower     =  x +y -yaw;
            double rightBackPower    =  x -y +yaw;

            // Normalize wheel powers to be less than 1.0
            double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Send powers to the wheels.
            driveTrain.setMotorSpeeds(rightFrontPower,rightBackPower,leftFrontPower,leftBackPower);
        }

        private  AprilTagDetection getDesiredTag(int DESIRED_TAG_ID) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            for (AprilTagDetection detection : currentDetections) {
                // Look to see if we have size info on this tag.
                if (detection.metadata != null) {
                    //  Check to see if we want to track towards this tag.
                    if ((DESIRED_TAG_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
                        return detection;
                    } else {
                        // This tag is in the library, but we do not want to track it right now.
                        telemetry.addData("Skipping", "Tag ID %d is not desired", detection.id);
                    }
                } else {
                    // This tag is NOT in the library, so we don't have enough information to track to it.
                    telemetry.addData("Unknown", "Tag ID %d is not in TagLibrary", detection.id);
                }
            }
            return null;
        }
    }

    public static class WaitUntilDetect extends AutoStep {
        IntSupplier targetID;
        public WaitUntilDetect(IntSupplier ID) {
            targetID = ID;
        }
        @Override
        public void init() {

        }

        @Override
        public void run() {
            telemetryAprilTag();
        }

        @Override
        protected void onFinish() {
        }

        @SuppressLint("DefaultLocale")
        private void telemetryAprilTag() {

            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            telemetry.addData("# AprilTags Detected", currentDetections.size());

            // Step through the list of detections and display info for each one.
            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    setFinished(detection.id == targetID.getAsInt());
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                    telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
                } else {
                    telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                    telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
                }
            }   // end for() loop

            // Add "key" information to telemetry
            telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
            telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
            telemetry.addLine("RBE = Range, Bearing & Elevation");

        }   // end method telemetryAprilTag()
    }
}
