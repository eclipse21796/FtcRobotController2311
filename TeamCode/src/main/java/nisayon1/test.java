package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class test extends LinearOpMode {
    @Override
    public void runOpMode(){


        DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel");
        DcMotor leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
        DcMotor hand1 = hardwareMap.dcMotor.get("hand1");
        Servo lucifer = hardwareMap.servo.get("lucifer"); // TODO: להוסיף לקונפיגורציה בדרייבר האב

        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        hand1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        boolean xIsPressed = false;

        while (opModeIsActive()) {
            telemetry.addData("height", hand1.getCurrentPosition()); //TODO: לבדוק מה הגובה שצריך לזרוע זה יהיה רשום בדרייבר האב
            telemetry.update();
        }
    }
}


