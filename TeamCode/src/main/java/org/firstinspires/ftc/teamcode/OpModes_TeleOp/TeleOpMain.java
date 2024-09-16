package org.firstinspires.ftc.teamcode.OpModes_TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Cogintilities.GamepadWrapper.GamepadWrapper;
import org.firstinspires.ftc.teamcode.Robot.RobotConfiguration;
import org.firstinspires.ftc.teamcode.Robot.TeamConstants;
import org.firstinspires.ftc.teamcode.SubSytems.WristSystem;

/* Ensure that the robot is physically in the initialize position. Slide and Pivot encoders will
be reset upon OpMode initialization.
 */
//@Disabled
@TeleOp(name="FairTeleOp", group="DurhamFair")
public class TeleOpMain extends RobotConfiguration implements TeamConstants {

    private boolean presetMotionInProgress = false;

    @Override
    public void runOpMode() throws InterruptedException {

        initializeRobot();

        GamepadWrapper driver   = new GamepadWrapper(gamepad1);
        GamepadWrapper operator = new GamepadWrapper(gamepad2);

        pivotJoint.resetEncoder();
        slideSys.resetEncoder();

        waitForStart();

        while (opModeIsActive()) {

            driver.update();
            operator.update();

            /* Move Robot? */
            drive.mecanumDrive(-driver.leftStick_Y, driver.leftStick_X, driver.rightStick_X, driver.right_bumper);

            /* Toggle Pincers? */
            if(operator.left_bumper)  pincers.toggleLeftPincer();
            if(operator.right_bumper) pincers.toggleRightPincer();

            if(!presetMotionInProgress) {
                /* Jog Slide? */
                slideSys.jog(operator.rightStick_Y);

                /* Jog Pivot Motor? */
                pivotJoint.jog(operator.leftStick_Y);

                /* Move Wrist? */
//                if(operator.dpad_up)   wristJoint.jog(WristSystem.Jog.WRIST_UP);
//                if(operator.dpad_down) wristJoint.jog(WristSystem.Jog.WRIST_DOWN);
            }

            /* Move Robot to Preset Configurations */
            if(operator.X) stowForTravel();
            if(operator.A) pickupFloorPixel();
            if(operator.B) scoreLowPosition();

            /* Drone Commands */
            if(driver.left_bumper && driver.X) drone.launchDrone();
            if(driver.left_bumper && driver.Y) drone.resetDroneLauncher();

            /* Check if preset movements have finished */
            if(slideSys.motionFinished() & pivotJoint.motionFinished() || operator.back) {
                presetMotionInProgress = false;
            }

            slideSys.update();
            pivotJoint.update();

            /* Add any Telemetry Items Here */
            telemetry.addData("Pivot Encoder Value: ", pivotJoint.getPivotPos());
            telemetry.addData("Slide Encoder Value: ", slideSys.getSlidePos());
//            telemetry.addData("Wrist Encoder Value: ", wristJoint.getServoPos());
            telemetry.update();
        }
    }


    /**
     * Position robot for travel to fit under bars
     */
    private void stowForTravel() {
        presetMotionInProgress = true;
        pivotJoint.moveToCountPosition(828, 0.5);
        slideSys.moveToCountPosition(50, 0.5);
//        wristJoint.setPosition(0.95);
    }


    /**
     * Move robot systems to pick up a pixel off the floor
     */
    private void pickupFloorPixel() {
        presetMotionInProgress = true;
        pivotJoint.moveToCountPosition(PIVOT_FLOOR_PICKUP, 0.5);
//        wristJoint.setPosition(WRIST_FLOOR_PICKUP);
        slideSys.moveToCountPosition(SLIDE_FLOOR_PICKUP, 0.5);
    }


    /**
     * Move robot systems to the low scoring position
     */
    private void scoreLowPosition() {
        presetMotionInProgress = true;
        pivotJoint.moveToCountPosition(PIVOT_SCORE_LOW, 0.5);
//        wristJoint.setPosition(WRIST_SCORE_LOW);
        slideSys.moveToCountPosition(SLIDE_FLOOR_PICKUP, 0.5);
    }
}
