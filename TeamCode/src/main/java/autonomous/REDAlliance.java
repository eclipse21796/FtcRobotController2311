package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public abstract class REDAlliance extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel");
        DcMotor leftWheel  = hardwareMap.dcMotor.get("leftWheel" );

        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        while (opModeIsActive()) {

            leftWheel.setPower(1);
            leftWheel.setTargetPosition(leftWheel.getCurrentPosition()-3000);
            leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightWheel.setPower(1);
            rightWheel.setTargetPosition(rightWheel.getCurrentPosition()-3000);
            rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }
}
}