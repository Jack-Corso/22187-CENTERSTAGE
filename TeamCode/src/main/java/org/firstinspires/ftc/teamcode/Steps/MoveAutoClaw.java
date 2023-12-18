package org.firstinspires.ftc.teamcode.Steps;

import org.firstinspires.ftc.teamcode.LinearAuto.AutoStep;
import org.firstinspires.ftc.teamcode.subsystems.AutoArm;

public class MoveAutoClaw extends AutoStep {
    AutoArm arm;
    private int cmd;
    public static final class LeftConstants {
        public static final int OPEN = 1;
        public static final int CLOSE = 2;
        public static final int OPEN_FULL = 7;
    }
    public static final class RightConstants {
        public static final int OPEN = 3;
        public static final int CLOSE = 4;
        public static final int OPEN_FULL = 8;
    }
    public static final class Constants {
        public static final int OPEN_BOTH = 5;
        public static final int CLOSE_BOTH = 6;
    }
    public MoveAutoClaw(int command) {
        if (command < 1 ||  command > 8) throw new IllegalArgumentException("Please provide a valid value (1-6)");
        cmd = command;
    }
    @Override
    public void init() {
        arm = new AutoArm(hardwareMap);
        switch(cmd) {
            case 1: arm.setLeft(AutoArm.LeftPresets.open); break;
            case 2: arm.setLeft(AutoArm.LeftPresets.close); break;
            case 3: arm.setRight(AutoArm.RightPresets.open); break;
            case 4: arm.setRight(AutoArm.RightPresets.close); break;
            case 5:
                arm.setLeft(AutoArm.LeftPresets.open);
                arm.setRight(AutoArm.RightPresets.open);
                break;
            case 6:
                arm.setLeft(AutoArm.LeftPresets.close);
                arm.setRight(AutoArm.RightPresets.close);
                break;
            case 7: arm.setLeft(AutoArm.LeftPresets.fullOpen);
            case 8: arm.setRight(AutoArm.RightPresets.fullOpen);
        }
        setFinished(true);
    }

    @Override
    public void run() {}

    @Override
    protected void onFinish() {}
}
