package org.firstinspires.ftc.teamcode.OpModes_Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.RobotConfiguration;
import org.firstinspires.ftc.teamcode.TeamConstants;

@Disabled
@Autonomous(name="AutoTeamColorDescriptor", group="Autonomous")
public class AutonomousOpTemplate extends RobotConfiguration implements TeamConstants {

    @Override
    public void runOpMode() throws InterruptedException {

        initializeRobot();
        /* First line of code after initializing the robot should be to set the alliance color.
           This is needed for April Tags, TFOD object files or any other game element that is unique
           to the Red or Blue Alliance. */
        setAlliance(AllianceColor.RED); /* OR */ setAlliance(AllianceColor.BLUE);

        waitForStart();

        while (opModeIsActive()) {

        }
    }
}
