package OptimusPrime;

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
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        boolean aIsPressed = false;
        boolean bIsPressed = false;


        while (opModeIsActive()) {

            leftWheel.setPower(1);
            rightWheel.setPower(1);

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

            servo1.setPosition(gamepad1.left_trigger-gamepad1.left_trigger);

         /*   if (gamepad1.x && !xIsPressed) { // לחצן X ב gamepad1
                xIsPressed = true; // כשהמצב של X משתנה ללחוץ
                if (lucifer.getPosition() == 1) // אם הסרבו נמצא בנקודה 1
                    lucifer.setPosition(0); // תעבור אותו לנקודה 0
                else lucifer.setPosition(1); // אם הסרבו נמצא בנקודה 0 תעבור לנקודה 1
            } else if (!gamepad1.x) xIsPressed = false; // אם X לא מופעל תשאיר אותו לא לחוץ*/

            //leftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //rightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            leftWheel.setPower(gamepad1.left_stick_y*(gamepad1.left_stick_y > 0? 1:0.65));
            rightWheel.setPower(gamepad1.right_stick_y*(gamepad1.right_stick_y > 0? 1:0.65));
            //*(gamepad1.left_stick_y > 0? 1:0.65));
            //*(gamepad1.right_stick_y > 0? 1:0.65));

            if (gamepad1.y)
            { hand1.setPower(1);
              //  rightWheel.setPower(1);

            }
            else {
                hand1.setPower(0);
               // rightWheel.setPower(0);
            }









              /*  double leftPower = gamepad1.right_stick_y + gamepad1.right_stick_x;
                  double rigthPower = gamepad1.right_stick_y - gamepad1.right_stick_x;


                      if (leftPower>1||rigthPower>1){
                        double maxPower = Math.max(leftPower,rigthPower);
                        leftPower = leftPower/maxPower;
                        rigthPower = rigthPower/maxPower;

                  leftWheel.setPower(leftPower);
                  rightWheel.setPower(rigthPower); */

            if (gamepad1.x)
            { hand1.setPower(-1);
              //  rightWheel.setPower(1);

            }
            else {
                hand1.setPower(0);
               // rightWheel.setPower(0);
            }

            telemetry.addData("height",hand1.getCurrentPosition());

            telemetry.addData("currentServoPozistion",servo1.getPosition());
            telemetry.update();

        }
    }
}


