package org.firstinspires.ftc.teamcode.OpModes_TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Cogintilities.Buttons.MomentaryButton;
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
    private Gamepad gp1, gp2;

    private final MomentaryButton leftPincerToggle = new MomentaryButton(false,false);
    private final MomentaryButton rightPincerToggle = new MomentaryButton(false, false);
    private final MomentaryButton wristUp = new MomentaryButton(false, false);
    private final MomentaryButton wristDown = new MomentaryButton(false, false);
    private final MomentaryButton lowAuto = new MomentaryButton(false, false);
    private final MomentaryButton degradedDriveMode = new MomentaryButton(false,false);
    private final MomentaryButton presetOverride = new MomentaryButton(false,false);

    @Override
    public void runOpMode() throws InterruptedException {

        initializeRobot();

        pivotJoint.resetEncoder();
        slideSys.resetEncoder();

        waitForStart();

        while (opModeIsActive()) {

            updateControls(gamepad1, gamepad2);

            /* Move Robot? */
            drive.mecanumDrive(-gp1.left_stick_y, gp1.left_stick_x, gp1.right_stick_x, degradedDriveMode.state());;

            /* Toggle Pincers? */
            if(leftPincerToggle.state())  pincers.toggleLeftPincer();
            if(rightPincerToggle.state()) pincers.toggleRightPincer();

            if(!presetMotionInProgress) {
                /* Jog Slide? */
                slideSys.jog(gp2.right_stick_y);

                /* Jog Pivot Motor? */
                pivotJoint.jog(gp2.left_stick_y);

                /* Move Wrist? */
//                if(wristUp.state())   wristJoint.jog(WristSystem.Jog.WRIST_UP);
//                if(wristDown.state()) wristJoint.jog(WristSystem.Jog.WRIST_DOWN);
            }

            /* Move Robot to Preset Configurations */
            if(gp2.x) stowForTravel();
            if(gp2.a) pickupFloorPixel();
            if(lowAuto.state()) scoreLowPosition();

            /* Drone Commands */
            if(gp1.left_bumper && gp1.x) drone.launchDrone();
            if(gp1.left_bumper && gp1.y) drone.resetDroneLauncher();

            /* Check if preset movements have finished */
            if(slideSys.motionFinished() & pivotJoint.motionFinished() || presetOverride.state()) {
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
     * Read GamePad Controls and update any Button objects
     * @param gamepd1 Gamepad 1 (driver)
     * @param gamepd2 Gamepad 2 (operator)
     */
    private void updateControls(Gamepad gamepd1, Gamepad gamepd2){

        gp1 = gamepd1;
        gp2 = gamepd2;

        /* Gamepad 1 Buttons */
        degradedDriveMode.update(gp1.right_bumper);

        /* Gamepad 2 Buttons */
        leftPincerToggle.update(gp2.left_bumper);
        rightPincerToggle.update(gp2.right_bumper);
        wristUp.update(gp2.dpad_up);
        wristDown.update(gp2.dpad_down);
        lowAuto.update(gp2.b);
        presetOverride.update(gp2.back);

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
