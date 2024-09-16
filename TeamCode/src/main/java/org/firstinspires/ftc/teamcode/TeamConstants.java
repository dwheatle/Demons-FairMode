package org.firstinspires.ftc.teamcode;

public interface TeamConstants {

    /*////////////////////////// Preset Positions \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/

    /* Pickup Pixel from Floor */
    int    PIVOT_FLOOR_PICKUP = 200;
    int    SLIDE_FLOOR_PICKUP = 400;
    double WRIST_FLOOR_PICKUP = 0.8628;


    /* Score low position */
    int    PIVOT_SCORE_LOW = 957;
    int    SLIDE_SCORE_LOW = 400;
    double WRIST_SCORE_LOW = 0.8730;


    /* Stow/Travel position */
    int    PIVOT_STOW = 820;
    int    SLIDE_STOW = 50;
    double WRIST_STOW = 0.95;


    /*~~~~~~~~~~~~~~~~~~~~~ MecanumDrive Subsystem Constants ~~~~~~~~~~~~~~~~~~~~~*/
    double DEGRADED_DRIVE_LIMIT  = 0.45;
    double DEGRADED_STRAFE_LIMIT = 0.35;
    double DEGRADED_TURN_LIMIT   = 0.25;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~ SlideSystem Constants ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    int RETRACT_LIMIT = -10;
    int EXTEND_LIMIT = 1675;
    int SLIDE_POSITION_TOLERANCE = 10;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~ PivotSystem Constants ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    int POS_LIMIT_LOW = 0;
    int POS_LIMIT_HIGH = 1600;
    int PIVOT_POSITION_TOLERANCE = 10;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~ WristSystem Constants ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    double MAX_ROTATION_SETPOINT = 0.96;
    double MIN_ROTATION_SETPOINT = 0.85;
    double JOG_INTERVAL = 0.009;


    /*~~~~~~~~~~~~~~~~~~~~~~~~ Pincer System Constants -~~~~~~~~~~~~~~~~~~~~~~~~~*/
    double OPEN_SETPT = 0.35;
    double CLOSE_SETPT = 0.6;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~ Drone System Constants ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    double STOW_LAUNCHER   = 0.60;
    double DEPLOY_LAUNCHER = 1.00;
}
