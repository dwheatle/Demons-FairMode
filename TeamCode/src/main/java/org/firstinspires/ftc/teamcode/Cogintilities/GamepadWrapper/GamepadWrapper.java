package org.firstinspires.ftc.teamcode.Cogintilities.GamepadWrapper;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Cogintilities.Buttons.MomentaryButton;

/**
 * This class provides a wrapper around the gamepad class to allow for extra functionality of the
 * buttons and joysticks. By default, all buttons are modified to be objects of the MomentaryButton
 * class. If a different behavior is desired, make the necessary changes below..
 */
public class GamepadWrapper {

    private final Gamepad gp;

    double leftStick_X, leftStick_Y, rightStick_X, rightStick_Y, leftTrigger, rightTrigger;
    Boolean A,B,X,Y,dpad_up,dpad_down,dpad_left,dpad_right,left_bumper, right_bumper, back, start, guide;

    /* Change button behavior here if needed */
    MomentaryButton btnA = new MomentaryButton(false,false);
    MomentaryButton btnB = new MomentaryButton(false,false);
    MomentaryButton btnX = new MomentaryButton(false,false);
    MomentaryButton btnY = new MomentaryButton(false,false);

    MomentaryButton btnDpadUp    = new MomentaryButton(false,false);
    MomentaryButton btnDpadDown  = new MomentaryButton(false,false);
    MomentaryButton btnDpadLeft  = new MomentaryButton(false,false);
    MomentaryButton btnDpadRight = new MomentaryButton(false,false);

    MomentaryButton btnLbumper = new MomentaryButton(false,false);
    MomentaryButton btnRbumper = new MomentaryButton(false,false);

    MomentaryButton btnBack  = new MomentaryButton(false,false);
    MomentaryButton btnStart = new MomentaryButton(false,false);
    MomentaryButton btnGuide = new MomentaryButton(false,false);;

    /**
     * Constructor
     * Creates a gamepad wrapper object.
     * @param gamepad Gamepad whose buttons are to be modified.
     */
    public GamepadWrapper(Gamepad gamepad) {
        gp = gamepad;
    }


    /**
     * This function calls the update function for all of the button objects. It should be called
     * once per loop in the opMode's runOpMode function.
     */
    public void update() {
        btnA.update(gp.a);
        btnB.update(gp.b);
        btnX.update(gp.x);
        btnY.update(gp.y);
        btnDpadUp.update(gp.dpad_up);
        btnDpadDown.update(gp.dpad_down);
        btnDpadLeft.update(gp.dpad_left);
        btnDpadRight.update(gp.dpad_right);
        btnLbumper.update(gp.left_bumper);
        btnRbumper.update(gp.right_bumper);
        btnBack.update(gp.back);
        btnStart.update(gp.start);
        btnGuide.update(gp.guide);

        updateVars();
    }


    /**
     * Update the values of the buttons and joysticks.
     */
    private void updateVars() {
        /* Assign Values */
        leftStick_X = gp.left_stick_x;
        leftStick_Y = gp.left_stick_y;
        rightStick_X = gp.right_stick_x;
        rightStick_Y = gp.right_stick_y;
        leftTrigger = gp.left_trigger;
        rightTrigger = gp.right_trigger;

        A = btnA.state();
        B = btnB.state();
        X = btnX.state();
        Y = btnY.state();

        dpad_up = btnDpadUp.state();
        dpad_down = btnDpadDown.state();
        dpad_left = btnDpadLeft.state();
        dpad_right = btnDpadRight.state();

        left_bumper = btnLbumper.state();
        right_bumper = btnRbumper.state();
        back = btnBack.state();
        start = btnStart.state();
        guide = btnGuide.state();

    }

}
