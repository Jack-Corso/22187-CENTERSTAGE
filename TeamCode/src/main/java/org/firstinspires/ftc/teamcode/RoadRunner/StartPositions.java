package org.firstinspires.ftc.teamcode.RoadRunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.LinearAuto.RoadRunner;

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

//    public static double vect01=-20.25;
//    public static double vect02=-30;
//    public static double vect11=-12;
//    public static double vect12=-28;
//    public static double vect21=-0.5;
//    public static double vect22=-36;
//    public static double vect31=-12;
//    public static double vect32=-40;

    public static Vector2d spikeBlueCloseRight = new Vector2d(2.5,-34);

    public static Vector2d[][] spikePositions = {
            {
                //0
                    //left
                    new Vector2d(-16.75,-34),
                    //middle
                    new Vector2d(-10,-26.5),
                    //right
                    new Vector2d(1.5,-33),
                    //"start" point
                    new Vector2d(-12,-40)
            },
            {
                //1
                    new Vector2d(1.5,35.25),
                    new Vector2d(-12,28),
                    new Vector2d(-17.75,34),
                    new Vector2d(-12,40),
            },
            {
//                2
//                    new Vector2d(vect01,vect02),
//                    new Vector2d(vect11,vect12),
//                    new Vector2d(vect21,vect22),
//                    new Vector2d(vect31,vect32)
                    new Vector2d(25.5,-36.25),
                    new Vector2d(36,-26.5),
                    new Vector2d(41.75,-34),
                    new Vector2d(36,-40)
            },
            {
                //3
                    new Vector2d(44.25,30),
                    new Vector2d(36,28),
                    new Vector2d(24.5,36),
                    new Vector2d(36,40)
            }
    };
    // the rotation of the robot when trying to place on the corresponding spike mark
    public static double[] spikeRotation = {
            Math.toRadians(0), //left | okay-change if needed
            Math.toRadians(0), //middle | good
            Math.toRadians(-90) //right | good
    };
    // the position that the robot starts the autonomous at | good, don't change
    public static Pose2d[] startPositions = {
            new Pose2d(-12.75,-63.75,Math.toRadians(90)), //close blue
            new Pose2d(-9.75,63.75,Math.toRadians(-90)), //close red
            new Pose2d(33.75,-63.75,Math.toRadians(90)), //far blue
            new Pose2d(33.75,63.75,Math.toRadians(-90)) //far red
    };
    //(*) These are the positions at which the camera can see and drive to the backdrop april tags
    public static Pose2d[] backboardPositions = {
            new Pose2d(-12,-48,Math.toRadians(180)), //blue side | good
            new Pose2d(-12,12,Math.toRadians(155)) //red side | x and y are good, verify angle
    };
    //TODO fill this out - this is the main goal
    /*
    these are the trajectory / trajectorySequences needed to get to the backboardPosition (*) from the loc and spike placement so that backboardBase can be called
    I recommend you start with going through the gate for the far sides - if you want to add an alternative path just add a boolean "alternative" argument
    If you want to move the robot to a more centered position between the spike marks after dropping, moving back 11 has worked well so far
     */
    public static AutoStep backBoardTransition(int loc, int pos /*ReadTfod.getResult() (spike mark)*/) {
        if (loc == 0) { //close blue | finished
            //no need for alternate positions here, this trajectory works will all 3 spike marks
            return RoadRunner.followTrajectorySequence(()->
                    RoadRunner.trajectorySequenceBuilder()
                            .back(12)
                            .lineToLinearHeading(StartPositions.backboardPositions[0])
                            .build()
            );
        } else if (loc == 1) { //close red
            if (pos == 1) { //middle
                /*
                      *------<
                             |
                    --O--    |
                 |        |  |
                 |    ----|--^
                 |        |
                 */
                return null; //TODO
            } else { //left & right
                /*
                      *
                      |
                    -----
                 |    |   |
                 O    |   O
                 |        |
                 */
                return null; //TODO
            }
        } else if (loc == 2) { //far blue
            if (pos == 0) { //left
                return null; //TODO
            }
            else if (pos == 1) { //middle
                return null; //TODO
            } else { // right
                return null; //TODO
            }
        } else { // far red
            if (pos == 0) { // left
                return null; //TODO
            }
            else if (pos == 1) { // middle
                return null; //TODO
            } else { // right
                return null; //TODO
            }
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
