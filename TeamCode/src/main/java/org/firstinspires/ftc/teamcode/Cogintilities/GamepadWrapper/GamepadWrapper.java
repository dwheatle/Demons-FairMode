package org.firstinspires.ftc.teamcode.Cogintilities.GamepadWrapper;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Cogintilities.Buttons.MomentaryButton;


public class GamepadWrapper {

    private final Gamepad gp;

    double leftStick_X, leftStick_Y, rightStick_X, rightStick_Y, leftTrigger, rightTrigger;
    Boolean A,B,X,Y,dpad_up,dpad_down,dpad_left,dpad_right,left_bumper, right_bumper, back, start, guide;
    MomentaryButton btnA, btnB, btnX, btnY,
                    btnDpadUp, btnDpadDown, btnDpadLeft, btnDpadRight,
                    btnLbumper, btnRbumper,
                    btnBack, btnStart, btnGuide;

    public GamepadWrapper(Gamepad gamepad) {
        gp = gamepad;

        btnA = new MomentaryButton(false,false);
        btnB = new MomentaryButton(false,false);
        btnX = new MomentaryButton(false,false);
        btnY = new MomentaryButton(false,false);

        btnDpadUp    = new MomentaryButton(false,false);
        btnDpadDown  = new MomentaryButton(false,false);
        btnDpadLeft  = new MomentaryButton(false,false);
        btnDpadRight = new MomentaryButton(false,false);

        btnLbumper = new MomentaryButton(false,false);
        btnRbumper = new MomentaryButton(false,false);

        btnBack   = new MomentaryButton(false,false);
        btnStart  = new MomentaryButton(false,false);
        btnGuide  = new MomentaryButton(false,false);
    }

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
