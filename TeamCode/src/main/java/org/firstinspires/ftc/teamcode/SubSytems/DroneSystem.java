package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeamConstants;

public class DroneSystem implements TeamConstants {

    private Servo droneSrvo;


    /**
     * CONSTRUCTOR
     * @param dronetServo
     */
    public DroneSystem(Servo dronetServo) {
        droneSrvo = dronetServo;
    }


    /**
     * Command servo to launch the drone
     */
    public void launchDrone(){
        droneSrvo.setPosition(DEPLOY_LAUNCHER);
    }


    /**
     * Reset the drone launcher
     */
    public void resetDroneLauncher(){
        droneSrvo.setPosition(STOW_LAUNCHER);
    }



}
