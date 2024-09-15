package org.firstinspires.ftc.teamcode.SubSytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Robot.TeamConstants;

public class PivotSystem implements TeamConstants {

    DcMotorEx pivotMotor;

    /** Preset Levels **/
    public final int POS_NEST = 20;
    public final int POS_FLOOR_PICKUP = 200;
    public final int POS_SCORE_LOW = 539;
    public final int POS_SCORE_HIGH = 1500;
    public final int POS_TRAVEL = 800;

    private double kp = 8;
    private double kf = 0;


    /**
     * CONSTRUCTOR
     * @param pivotMotr Pivot motor name assigned in the hardware map.
     */
    public PivotSystem(DcMotorEx pivotMotr) {
        pivotMotor = pivotMotr;
        pivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        setPidParms(kp);
    }


    /**
     * Manually jog the pivot motor
     * @param command
     */
    public void jog(double command) {
        pivotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(command > 0.05 || command < -0.05) {                     // Dead band
            pivotMotor.setPower(0.6 * clampPowerSetpt(command));
        } else {
            moveToCountPosition(getPivotPos(), 0.25);
        }
    }


    /**
     * Move the pivot motor to a desired encoder count
     * @param trgtPosCnts
     * @param duration_sec
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

        int currentPosition = pivotMotor.getCurrentPosition();
        double rate = 100; // [counts/sec]

        if(duration_sec > 0) {
            rate = (trgtPosCnts - currentPosition) / duration_sec;
        } else rate = 100;

        pivotMotor.setTargetPosition(clampPositionSetpt(trgtPosCnts));
        pivotMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        pivotMotor.setVelocity(rate);
    }


    /**
     * Returns if the system has reached its set point
     * @return
     */
    public boolean motionFinished() {
        int error = Math.abs(pivotMotor.getCurrentPosition() - pivotMotor.getTargetPosition());
        if (error < PIVOT_POSITION_TOLERANCE) return true; else return false;
    }


    /**
     * Checks to ensure the system is operating within its boundaries.  Should be called once per
     * OpMode loop.
     */
    public void update() {
        int currentPos = pivotMotor.getCurrentPosition();

        if (currentPos > POS_LIMIT_HIGH || currentPos < POS_LIMIT_LOW) {
            pivotMotor.setPower(0);
        }
    }


    /**
     * Clamp requester power to the motors boundaries.
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
     * Clamp requested encoder count to physical motion boundaries
     * @param setPoint
     * @return
     */
    public int clampPositionSetpt(int setPoint) {
        if (setPoint > POS_LIMIT_HIGH) {
            setPoint = POS_LIMIT_HIGH;
        } else if (setPoint < POS_LIMIT_LOW) {
            setPoint = POS_LIMIT_LOW    ;
        }
        return setPoint;
    }


    /**
     * Reset the Pivot motor encoder count
     */
    public void resetEncoder() {
        pivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /**
     * Set the PID gain.
     * @param pGain
     */
    public void setPidParms(double pGain) {
        kp = pGain;
        pivotMotor.setPositionPIDFCoefficients(kp);
    }


    /**
     * Set the Feed Forward term.
     * @param feedForwardGain
     */
    public void setFeedForwardTerm(double feedForwardGain) {
        //PIDFCoefficients gains = new PIDFCoefficients(kp,0,0,feedForwardGain);
        //pivotMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, gains);
    }


    /** Accessors **/
    public int getPivotPos() {return pivotMotor.getCurrentPosition(); }
    public double getPivotPower() {return pivotMotor.getPower(); }
    //public double getPivotCurrent() {return pivotMotor.getCurrent(CurrentUnit.MILLIAMPS); }

}
