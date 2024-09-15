package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Cogintilities.VisionProcessors.VisionPortalObject;
import org.firstinspires.ftc.teamcode.SubSytems.ClimberSystem;
import org.firstinspires.ftc.teamcode.SubSytems.DroneSystem;
import org.firstinspires.ftc.teamcode.SubSytems.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubSytems.PincerSystem;
import org.firstinspires.ftc.teamcode.SubSytems.PivotSystem;
import org.firstinspires.ftc.teamcode.SubSytems.SlideSystem;
import org.firstinspires.ftc.teamcode.SubSytems.WristSystem;

import java.util.List;

/**
 * This class should be used to define all the subsystem modules and assign the hardware used in
 * those modules. The keyword 'abstract' indicates that an object of this class cannot be created
 * directly in an opMode. Instead, a class must be created that extends or inherits from this class.
 * In our case, all OpModes will extend RobotConfig. This allows the opMode to use all the
 * variables, objects and methods defined below. It also will create an OpMode that uses the SDK's
 * LinearOpMode framework as this class itself extends the LinearOpMode class.
 */
public abstract class RobotConfiguration extends LinearOpMode {

    /*------------ Public Class Variables - Frowned Upon ------------*/
    public enum AllianceColor { RED, BLUE }
    // public int exampleVariable = 0;


    /*------------- Private Class Variables - Preferred -------------*/
    static AllianceColor alliance;
    /* keyword 'static' indicates that this variable exists in only one memory location. Static
    attributes and methods belong to the class rather than the object.  In this case, all classes
    that extend this class will have the same value for 'alliance'. This allows the value to persist
    through autonomous and teleOp modes */

    /*----------- Define all Module Classes (SubSystems) -----------*/
    protected MecanumDrive drive = null;
    protected VisionPortalObject vision = null;
    protected PincerSystem pincers = null;
    protected PivotSystem pivotJoint = null;
    protected SlideSystem slideSys = null;
    protected WristSystem wristJoint = null;
    protected DroneSystem drone = null;
//    protected ClimberSystem climb = null;
    /* keyword 'protected' is similar to 'private'. Private variables are only accessible within the
    scope of this class. Protected allows for all classes that extend or inherit from this class to
    directly access the variables, objects and methods. */

    /**
     * initializeRobot:
     * This function should be called immediately in the OpMode's runOpMode function. A null value
     * error will result if you try to use any devices connected to the control hub that
     * have not been initialized.  This function creates the Hardware Map and the module objects
     * that use these devices.
     *
     * @throws InterruptedException
     */
    public void initializeRobot() throws InterruptedException {

        /* Find all Control Hubs and Set Sensor Bulk Read Mode to AUTO */
        List<LynxModule> ctrlHubs;
        ctrlHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : ctrlHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        /* ******************* Define Hardware Map Here ******************** */
        DcMotorEx driveMotorLF = hardwareMap.get(DcMotorEx.class, "leftFront");
        DcMotorEx driveMotorRF = hardwareMap.get(DcMotorEx.class, "rightFront");
        DcMotorEx driveMotorLR = hardwareMap.get(DcMotorEx.class, "leftRear");
        DcMotorEx driveMotorRR = hardwareMap.get(DcMotorEx.class, "rightRear");

        DcMotorEx armSlideMotor = hardwareMap.get(DcMotorEx.class, "armSlide");
        DcMotorEx armPivotMotor = hardwareMap.get(DcMotorEx.class, "armPivot");

        Servo leftPincer = hardwareMap.get(Servo.class, "leftPincer");
        Servo rightPincer = hardwareMap.get(Servo.class, "rightPincer");
        Servo droneServo = hardwareMap.get(Servo.class, "droneServo");
        Servo wristServo = hardwareMap.get(Servo.class, "wristServo");
        Servo climberServo = hardwareMap.get(Servo.class, "climbServo");

        WebcamName webCam  = hardwareMap.get(WebcamName.class, "Webcam 1");

        /** Create an object of every module/subsystem needed for both autonomous and teleOp modes. **/
        drive  = new MecanumDrive(driveMotorLF, driveMotorLR, driveMotorRF, driveMotorRR);
        pincers    = new PincerSystem(leftPincer, rightPincer);
        pivotJoint = new PivotSystem(armPivotMotor);
        slideSys   = new SlideSystem(armSlideMotor);
        wristJoint = new WristSystem(wristServo);
        drone      = new DroneSystem(droneServo);
//        climb      = new ClimberSystem(climberServo);
        vision = new VisionPortalObject(webCam);
    }


    /**
     * runOpMode must be Overridden in all OpModes
     * This is a requirement from the LinearOpMode class in the SDK
     */
    @Override
    public abstract void runOpMode() throws InterruptedException;


    /* ********* Setters, Getters, Utility and Helper Functions ********** */
    public void setAlliance(AllianceColor color){ alliance = color; }
    public static AllianceColor getAlliance(){ return alliance; }


}
