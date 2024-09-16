package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeamConstants;

public class PincerSystem implements TeamConstants {

    private final Servo leftPincer, rightPincer;

    private enum PincerState {
        OPEN,
        CLOSED
    };

    PincerState left_pos;
    PincerState right_pos;


    /**
     * Constructor: Creates an object to control the pixel pincers.
     *
     * @param leftServo     Left servo name as assigned in the hardware map
     * @param rightServo    Right servo name as assigned in the hardware map
     */
    public PincerSystem(Servo leftServo, Servo rightServo) {

        leftPincer = leftServo;
        rightPincer = rightServo;

        leftPincer.setDirection(Servo.Direction.REVERSE);
        rightPincer.setDirection(Servo.Direction.FORWARD);

        left_pos = PincerState.OPEN;
        right_pos = PincerState.OPEN;

    }


    /**
     * Toggles the Left Pincer
     */
    public void toggleLeftPincer() {

        switch (left_pos) {
            case OPEN:
                leftPincer.setPosition((CLOSE_SETPT));
                left_pos = PincerState.CLOSED;
                break;
            case CLOSED:
                leftPincer.setPosition(OPEN_SETPT);
                left_pos = PincerState.OPEN;
                break;
        }
    }


    /**
     * Toggles the right pincer
     */
    public void toggleRightPincer() {

        switch (right_pos) {
            case OPEN:
                rightPincer.setPosition((CLOSE_SETPT));
                right_pos = PincerState.CLOSED;
                break;
            case CLOSED:
                rightPincer.setPosition(OPEN_SETPT);
                right_pos = PincerState.OPEN;
                break;
        }
    }


    /**
     * Open both pincers
     */
    public void openPincers() {

        leftPincer.setPosition(OPEN_SETPT);
        rightPincer.setPosition(OPEN_SETPT);

        left_pos = PincerState.OPEN;
        right_pos = PincerState.OPEN;
    }


    /**
     * Close both pincers
     */
    public void closePincers() {

        leftPincer.setPosition(CLOSE_SETPT);
        rightPincer.setPosition(CLOSE_SETPT);

        left_pos = PincerState.CLOSED;
        right_pos = PincerState.CLOSED;
    }

}
