package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class autonomouscameraredTEST extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        cameraTest camera = new cameraTest(hardwareMap,this);
        waitForStart();
    }
}

