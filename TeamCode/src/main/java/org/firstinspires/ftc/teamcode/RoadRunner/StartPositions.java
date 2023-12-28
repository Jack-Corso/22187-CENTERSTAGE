package org.firstinspires.ftc.teamcode.RoadRunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class StartPositions {
    public static Vector2d[][] spikePositions = {
            {
                    new Vector2d(-19.75,-37.5),
                    new Vector2d(-9.75,-30),
                    new Vector2d(-0.5,-37.5),
                    new Vector2d(-9.75,-40)
            },
            {
                    new Vector2d(0.5,37.5), //swap
                    new Vector2d(-9.75,30),
                    new Vector2d(-19.75,37.5),//swap
                    new Vector2d(-9.75,50),
            },
            {
                    new Vector2d(46.5,-37.5), // swap
                    new Vector2d(57.75,-30),
                    new Vector2d(65.75,-37.5), // swap
                    new Vector2d(57.75,-40)
            },
            {
                    new Vector2d(67.75,37.5),
                    new Vector2d(57.75,30),
                    new Vector2d(48.5,37.5),
                    new Vector2d(33.75,40)
            }
    };
    public static double[] spikeRotation = {
            Math.toRadians(45),
            Math.toRadians(0),
            Math.toRadians(-90)
    };
    public static Pose2d[] startPositions = {
            new Pose2d(-9.75,-66,Math.toRadians(90)),
            new Pose2d(-9.75,66,Math.toRadians(270)),
            new Pose2d(57.75,-66,Math.toRadians(90)),
            new Pose2d(57.75,66,Math.toRadians(270))
    };

}
