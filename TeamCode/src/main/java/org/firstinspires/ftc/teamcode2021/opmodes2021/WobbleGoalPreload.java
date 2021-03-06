package org.firstinspires.ftc.teamcode2021.opmodes2021;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import java.util.ArrayList;

import static java.lang.Math.atan;

//@Autonomous
public class WobbleGoalPreload extends Autonomous2021{
    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();

        ArrayList<Trajectory> trajectories = new ArrayList<Trajectory>();

        trajectories.addAll(detectStack());

        drive.followTrajectory(trajectories.get(0));

        drive.turn(FP*atan(1.0/2.0)+ Math.toRadians(90.0));
        state = getRingState();
        drive.turn(-drive.getPoseEstimate().getHeading()+Math.toRadians(-15.0));

        trajectories.addAll(doRingStackAndWobbleGoalPreload());

        trajectories.addAll(oneWobbleGoal(trajectories.get(trajectories.size()-1).end().vec()));

        drive.followTrajectory(trajectories.get(1));
        if (RedAlliance == (state == 1)){
            drive.turn(Math.toRadians(180.0));
        }
        dropOffWobbleGoal();
        drive.followTrajectory(trajectories.get(2));

        drive.setMotorPowers(0,0,0,0);
    }
}
