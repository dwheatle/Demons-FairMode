package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeamConstants;

public class SlideSystem implements TeamConstants {

    DcMotorEx slideMotor;

    /** Preset Levels **/
    public final int POS_NEST = 20;
    public final int POS_FLOOR_PICKUP = 300;
    public final int POS_SCORE_LOW = 493;
    public final int POS_SCORE_HIGH = 1600;
    public final int POS_TRAVEL = 40;

    private final double CTRL_POWER = 0.6;
    private final double CTRL_RATE = 300;  /** counts/sec **/

    private double kp = 6;
    private double kf = 0;
    private final int POS_TOLERANCE = 25;


    /**
     * CONSTRUCTOR
     */
    public SlideSystem(DcMotorEx slideMtr) {
        slideMotor = slideMtr;
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        setPidParms(kp);

        /** Zero Encoder - This may not be the right place **/
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /**
     * Clamp set point to be in range of the motor
     * @param setPoint
     * @return
     */
    public double clampPowerSetpt(double setPoint) {

        if (setPoint > 1.0) {
            setPoint = 1.0;
        } else if (setPoint < -1.0) {
            setPoint = -1.0;
        }
        return setPoint;
    }


    /**
     * Clamp position set point to be within valid slide boundaries
     * @param setPoint
     * @return
     */
    public int clampPositionSetpt(int setPoint) {
        if (setPoint > EXTEND_LIMIT) {
            setPoint = EXTEND_LIMIT;
        } else if (setPoint < RETRACT_LIMIT) {
            setPoint = RETRACT_LIMIT;
        }
        return setPoint;
    }


    /**
     * Manually jog the slide
     * @param command
     */
    public void jog(double command) {
        // Check motion limits prior to setting power
        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(command > 0.05 || command < -0.05) {                     // Dead band
            slideMotor.setPower(-0.5 * clampPowerSetpt(command));
        } else {
            slideMotor.setPower(0);
        }
    }


    /**
     * Move the system to a specified encoder value
     * @param trgtPosCnts   Target encoder count value
     * @param duration_sec  Desired time to achieve position
     */
    public void moveToCountPosition(int trgtPosCnts, double duration_sec) {

        /** https://docs.revrobotics.com/duo-control/programming/using-encoder-feedback#choosing-a-motor-mode
         *  https://ftctechnh.github.io/ftc_app/doc/javadoc/com/qualcomm/robotcore/hardware/DcMotorEx.html
         *
         *  DcMotorEx method RUN_TO_POSITION must be done in the following order:
         *      1. Set target position [encoder counts]
         *      2. Set motor mode to RUN_TO_POSITION
         *      3. Set the maximum velocity you want the motor to move
         *          motor.setVelocity(rate) [counts/sec]
         *          motor.setVelocity(rate, units) [ AngleUnit.DEGREES/sec or AngleUnit.RADIANS/sec]
         */

//            pivotMotor.setPositionPIDFCoefficients(2);
//            pivotMotor.setTargetPositionTolerance(40);

        int currentPosition = slideMotor.getCurrentPosition();
        double rate = (trgtPosCnts - currentPosition) / duration_sec;

        slideMotor.setTargetPosition(clampPositionSetpt(trgtPosCnts));
        slideMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotor.setVelocity(rate);

    }


    /**
     * Returns if the system has reached its target value
     * @return
     */
    public boolean motionFinished() {
        int error = Math.abs(slideMotor.getCurrentPosition() - slideMotor.getTargetPosition());
        if (error < SLIDE_POSITION_TOLERANCE) return true; else return false;
    }


    /**
     * Checks to ensure the system is operating within its boundaries.  Should be called once per
     * OpMode loop.
     */
    public void update() {

        int currentPos = slideMotor.getCurrentPosition();

        if (currentPos > EXTEND_LIMIT || currentPos < RETRACT_LIMIT) {
            slideMotor.setPower(0);
        }
    }


    /**
     * Allow dynamic setting of PID gain value if needed
     * @param pGain
     */
    public void setPidParms(double pGain) {
        kp = pGain;
        slideMotor.setPositionPIDFCoefficients(kp);
    }


    /**
     * Allow dynamic setting of the Feed Forward term.  May be used to account for arm andle
     * and/or extension
     * @param FFgain
     */
    public void setFeedForward(double FFgain) {
        kf = FFgain;
        slideMotor.setPositionPIDFCoefficients(kp);

    }


    /**
     * Reset the slide motor encoder
     */
    public void resetEncoder() {
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /** Accessors **/
    public int getSlidePos() {return slideMotor.getCurrentPosition(); }
    public double getSlidePower() {return slideMotor.getPower(); }

}
