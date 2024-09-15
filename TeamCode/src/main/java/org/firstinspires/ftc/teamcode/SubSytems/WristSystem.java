package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot.TeamConstants;

public class WristSystem implements TeamConstants {

    Servo wristServo;
    double currentPosition;

    public enum Jog { WRIST_UP, WRIST_DOWN }

    /****** CHECK THESE WITH THE FINAL VALUES TO AVOID SERVO BURNOUT ********/
    public final double POS_NEST = .95;
    public final double POS_FLOOR_PICKUP = 0.8628;  //.45;
    public final double POS_SCORE_LOW = 0.8744;     //.4;
    public final double POS_SCORE_HIGH = 0.8588;    //.4;
    public final double SERVO_FLOOR_DROP = .8744;

    /**
     * CONSTRUCTOR
     * Create an object to control the wrist system
     * @param Servo Servo name as defined in the hardware map.
     */
    public WristSystem(Servo Servo) {

        wristServo = Servo;
        currentPosition = wristServo.getPosition();
    }


    /**
     * Restricts the set point to the limits specified in the constants file
     * @param setPoint
     * @return valid in range set point value
     */
    private double clampSetpt(double setPoint) {
        if (setPoint > MAX_ROTATION_SETPOINT) {
            setPoint = MAX_ROTATION_SETPOINT;
        } else if (setPoint < MIN_ROTATION_SETPOINT) {
            setPoint = MIN_ROTATION_SETPOINT;
        }
        return setPoint;
    }


    /**
     * Move move wrist to the requested set point.  Set point will be clamped if outside the declared
     * range of motion.
     * @param setPoint
     */
    public void setPosition(double setPoint) {
        wristServo.setPosition(clampSetpt(setPoint));
    }


    /**
     * Jog the servo in the direction requested by the amount specified in the JOB_INTERVAL constant
     * @param dir
     */
    public void jog(Jog dir) {
        switch (dir) {
            case WRIST_UP:
                currentPosition = clampSetpt(getServoPos() + JOG_INTERVAL);
                break;
            case WRIST_DOWN:
                currentPosition = clampSetpt(getServoPos() - JOG_INTERVAL);
                break;
        }
        setPosition(currentPosition);
    }


    /** Accessors **/
    public double getServoPos() { return wristServo.getPosition(); }

}

