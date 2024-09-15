package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class ClimberSystem {
    private Servo climbServo;

    private boolean isOpenClimber = true;
    public double STOW_POSITION = 0.425;

    /** /////////////////////////// CONSTRUCTOR \\\\\\\\\\\\\\\\\\\\\\\\\\\ **/
    public ClimberSystem(Servo servo) {
        climbServo = servo;
    }

    /*************************************************************************/
    public void manualPosition(double position) {
        climbServo.setPosition(position);
    }

    public void climberToggle() {
        if(isOpenClimber) {
            climbServo.setPosition(0.0);
            isOpenClimber = false;
        } else {
            climbServo.setPosition(1.0);
            isOpenClimber = true;
        }
    }

    /*************************************************************************/
    public void disable() {
        ((ServoImplEx)climbServo).setPwmDisable();
    }

    /*************************************************************************/
    public void enable() {
        ((ServoImplEx)climbServo).setPwmEnable();
    }

}
