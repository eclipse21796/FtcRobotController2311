package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class test extends LinearOpMode {


    public void runOpMode(){


        DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel");
        DcMotor leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
        DcMotor hand1 = hardwareMap.dcMotor.get("hand1");
        Servo lucifer = hardwareMap.servo.get("lucifer");
        Servo servo1 = hardwareMap.servo.get("servo1");

        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        hand1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        boolean aIsPressed = false;
        boolean bIsPressed = false;


        while (opModeIsActive()) {

            if (gamepad1.x && !aIsPressed){
                aIsPressed = true;
                servo1.setPosition(servo1.getPosition() +0.1);
            }
            else if (!gamepad1.x) aIsPressed = false;

            if (gamepad1.b && !bIsPressed){
                bIsPressed = true;
                servo1.setPosition(servo1.getPosition() -0.1);
            }
            else if (!gamepad1.b) bIsPressed = false;


            telemetry.addData("height", hand1.getCurrentPosition());

            telemetry.addData("currentServoPozistion", servo1.getPosition());
            telemetry.update();

        }
    }
}


