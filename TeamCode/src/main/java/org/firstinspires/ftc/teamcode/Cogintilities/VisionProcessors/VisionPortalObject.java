package org.firstinspires.ftc.teamcode.Cogintilities.VisionProcessors;

import android.util.Size;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.concurrent.TimeUnit;

public class VisionPortalObject {

    /** WebCam1 Camera Settings **/
    WebcamName camera = null;
    private final long CAMERA_EXPOSURE = 6; //15;
    private final int  CAMERA_GAIN = 250;

    /** The variable to store our instance of the vision portal **/
    private VisionPortal visionPortal = null;
    private AprilTagProcessor aTagP = null;


    /**
     * Create a Vision Portal object with the specified camera.
     * @param cameraName
     */
    public VisionPortalObject(WebcamName cameraName) {
        camera = cameraName;
    }


    /**
     * Add an April Tag processor to the vision portal.
     * @param atproc
     * @throws InterruptedException
     */
    public void buildVisionPortal(AprilTagProcessor atproc) throws InterruptedException {

        aTagP = atproc;

        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .addProcessors(atproc)
                .setCameraResolution(new Size(640, 480))
                .build();

        /** Pause code execution until camera state is streaming **/
        while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
        }
        setManualExposure(CAMERA_EXPOSURE, CAMERA_GAIN);

    }

    /**
     * Manually set the camera gain and exposure.
     * This can only be called AFTER calling initAprilTag(), and only works for Webcams;
     */
    public void setManualExposure(long exposureMS, int cameraGain) {

        if (visionPortal == null) {
            return;
        }

        /** Adjust exposure and gain settings to reduce motion blur **/
        ExposureControl exposure = visionPortal.getCameraControl(ExposureControl.class);
        if (exposure.isExposureSupported()) {
            exposure.setMode(ExposureControl.Mode.Manual);
            exposure.setExposure(exposureMS, TimeUnit.MILLISECONDS);

            GainControl gain = visionPortal.getCameraControl(GainControl.class);
            gain.setGain(cameraGain);
        }
    }

    /*************************** Processor Controls ***************************/
    public void enableAprilTagDetection()  { visionPortal.setProcessorEnabled(aTagP, true); }
    public void disableAprilTagDetection() {visionPortal.setProcessorEnabled(aTagP, false); }


    /**
     * Return the frames/second the vision portal is capturing. Can be used to assess cpu memory usage
     */
    public float fps() { return visionPortal.getFps(); }


}
