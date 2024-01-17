package org.firstinspires.ftc.teamcode.RoadRunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RoadRunner;
import org.firstinspires.ftc.teamcode.LinearAuto.RunIf;
import org.firstinspires.ftc.teamcode.LinearAuto.RunnableStep;
import org.firstinspires.ftc.teamcode.LinearAuto.ToTelemetry;
import org.firstinspires.ftc.teamcode.Odometry;

import java.util.function.IntSupplier;

@Config
public class StartPositions {
    //todo you can skim most of this except for backboardTransition(),
    // learnRoadRunner is a much better source than me for trajectory stuff, so just look there if you cant figure something out with trajectories

    /* KEY (these should be on the whiteboard, but just in case):
    These numbers also correspond with the array indexes
    locations:
        0-close blue
        1-close red
        2-far blue
        3-far red
    positions (spike mark):
        0-left
        1-middle
        2-right
        (these values are the same as those returned by ReadTfod.getResult())
    Colors:
        0-blue
        1-red
        (you can find the color of a location by calling [location] % 2)
     */

    //FINISHED...
    //but drop positions on spike 0 for locations 1 and 3 can potentially be improved by changing the angle you approach the spike mark at
    //others work well
    public static Vector2d[][] spikePositions = {
            {
                    //left
                    new Vector2d(-19,-30),
                    //middle
                    new Vector2d(-12,-28),
                    //right
                    new Vector2d(-0.5,-36),
                    //"start" point
                    new Vector2d(-12,-40)
            },
            { //close red
                    //left
                    new Vector2d(0,33.25),
                    //middle
                    new Vector2d(-12,27),
                    //right
                    new Vector2d(-17.75,34),
                    //"mid"-point
                    new Vector2d(-12,44),
            },
            { //far blue
                    //left
                    new Vector2d(23.5,-35.25),
                    //middle
                    new Vector2d(36,-28),
                    //right
                    new Vector2d(41.75,-34),
                    //"mid"-point
                    new Vector2d(36,-40)
            },
            { //far red
                    //left
                    new Vector2d(43.25,30),
                    //middle
                    new Vector2d(36,28),
                    //right
                    new Vector2d(24.5,36),
                    //"mid"-point
                    new Vector2d(36,40)
            }
    };
    // the rotation of the robot when trying to place on the corresponding spike mark
    public static double[] spikeRotation = {
            Math.toRadians(30), //left | okay-change if needed
            Math.toRadians(0), //middle | good
            Math.toRadians(-90) //right | good
    };
    // the position that the robot starts the autonomous at | good, don't change
    public static Pose2d[] startPositions = {
            new Pose2d(-9.75,-63.75,Math.toRadians(90)), //close blue
            new Pose2d(-9.75,63.75,Math.toRadians(-90)), //close red
            new Pose2d(33.75,-63.75,Math.toRadians(90)), //far blue
            new Pose2d(33.75,63.75,Math.toRadians(-90)) //far red
    };
    //(*) These are the positions at which the camera can see and drive to the backdrop april tags
    public static Pose2d[] backboardPositions = {
            new Pose2d(-12,-48,Math.toRadians(180)), //blue side | good
            new Pose2d(-20.5,12,Math.toRadians(170)) //red side | x and y are good, verify angle
    };
    //TODO fill this out - this is the main goal
    /*
    these are the trajectory / trajectorySequences needed to get to the backboardPosition (*) from the loc and spike placement so that backboardBase can be called
    I recommend you start with going through the gate for the far sides - if you want to add an alternative path just add a boolean "alternative" argument
    If you want to move the robot to a more centered position between the spike marks after dropping, moving back 11 has worked well so far
     */
    public static AutoStep backBoardTransition(int loc, IntSupplier pos /*ReadTfod.getResult() (spike mark)*/) {
        if (loc == 0) { //close blue | finished
            //no need for alternate positions here, this trajectory works will all 3 spike marks
            return RoadRunner.followTrajectorySequence(()->
                    RoadRunner.trajectorySequenceBuilder()
                            .back(12)
                            .lineToLinearHeading(StartPositions.backboardPositions[0])
                            .build()
            );
        } else if (loc == 1) { //close red
            return new RunIf(()->pos.getAsInt()==1,
                     /*
                      *------<
                             |
                    --O--    |
                 |        |  |
                 |    ----|--^
                 |        |
                 */
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(11)
                                    .splineToConstantHeading(new Vector2d(-24,35.75),RoadRunner.driveTrain().getPoseEstimate().getHeading())
                                    //.splineToLinearHeading(new Pose2d(-24,24,backboardPositions[1].getHeading()),backboardPositions[1].getHeading())
                                    //.lineTo(new Vector2d(-36,2))
                                    .splineToLinearHeading(backboardPositions[1],backboardPositions[1].getHeading())
                                    .build()
                            )
            ).elseIfDo(()->pos.getAsInt()==2,
                    /*
                      *
                      |
                    -----
                 |    |   |
                 O    |   O
                 |        |
                 */
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(12)
                                    .lineToLinearHeading(new Pose2d(-12,12,backboardPositions[1].getHeading()))
                                    .splineToConstantHeading(backboardPositions[1].vec().plus(new Vector2d(8)),backboardPositions[1].getHeading())
                                    .build()
                            )
            ).elseDo(
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(16)
                                    .lineToLinearHeading(new Pose2d(-12,12,backboardPositions[1].getHeading()))
                                    .splineToConstantHeading(backboardPositions[1].vec(),backboardPositions[1].getHeading())
                                    .build()
                    )
            );
        } else if (loc == 2) { //far blue
            return new RunIf(()->pos.getAsInt()!=2,
                     RoadRunner.followTrajectorySequence(()->
                             RoadRunner.trajectorySequenceBuilder()
                                     .back(16)
                                     .lineToLinearHeading(new Pose2d(34,-60,Math.toRadians(90)))
                                     //.lineToConstantHeading(backboardPositions[0].vec())
                                     .lineToConstantHeading(new Vector2d(-18,-60))
                                     .lineToLinearHeading(backboardPositions[0].plus(new Pose2d(-2)))
                                     .build()

                     )
            ).elseDo(
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(12)
                                    .lineToLinearHeading(new Pose2d(34,-60,Math.toRadians(90)))
                                    //.lineToConstantHeading(backboardPositions[0].vec())
                                    .lineToConstantHeading(new Vector2d(-18,-60))
                                    .lineToLinearHeading(backboardPositions[0].plus(new Pose2d(-2)))
                                    .build()

                    )
            );
        } else { // far red
            return new RunIf(()->pos.getAsInt()==0,
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(16)
                                    .lineToLinearHeading(new Pose2d(34,12,0))
                                    .splineToLinearHeading(backboardPositions[1],backboardPositions[1].getHeading())
                                    .build()
                    )
            ).elseIfDo(()->pos.getAsInt()==1,
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(4)
                                    .splineToConstantHeading(new Vector2d(10,32),startPositions[3].getHeading())
                                    .turn(Math.toRadians(-90))
                                    .lineTo(new Vector2d(10,12)) //.strafeTo(10,12)
                                    .splineToLinearHeading(backboardPositions[1],backboardPositions[1].getHeading())
                                    .build()
                            )
            ).elseDo(
                    RoadRunner.followTrajectorySequence(()->
                            RoadRunner.trajectorySequenceBuilder()
                                    .back(11)
                                    .lineToLinearHeading(new Pose2d(34,12,0))
                                    .splineToLinearHeading(backboardPositions[1],backboardPositions[1].getHeading())
                                    .build()
                    )
            );
        }
    }
    //"old" code, can be ignored
    public static AutoStep[] backboardTransitions = {
            // This kinda works, I wouldn't use it tho
            RoadRunner.followTrajectorySequence(()->
                    RoadRunner.trajectorySequenceBuilder()
                            .back(12)
                            .lineToLinearHeading(new Pose2d(-12,55,Math.toRadians(180)))
                            .splineTo(new Vector2d(-36,55),Math.toRadians(180))
                            .splineTo(new Vector2d(-36,12),Math.toRadians(180))
                            .lineToLinearHeading(backboardPositions[1])
                            .build()
                            // .lineTo(StartPositions.backboardPositions[1].vec())
            ),
    };
}
