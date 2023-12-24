package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
    public class sofsofmasheu extends LinearOpMode {
        @Override
        public void runOpMode(){


            DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel");
            DcMotor leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
            DcMotor hand1 = hardwareMap.dcMotor.get("hand1");
            DcMotor hand2 = hardwareMap.dcMotor.get("hand2");
            Servo lucifer = hardwareMap.servo.get("lucifer");
            Servo servo1 = hardwareMap.servo.get("servo1");

            rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();
            if(isStopRequested()){return;}
            resetRuntime();

            boolean xIsPressed = false;
            boolean BIsPressed = false;


            while (opModeIsActive()) {

                leftWheel.setPower(gamepad1.right_stick_y);
                rightWheel.setPower(gamepad1.left_stick_y);
                hand1.setPower((gamepad2.right_trigger)*0.8);
                hand1.setPower((gamepad2.left_trigger)*0.8);
                hand2.setPower((-gamepad1.left_trigger)*0.8);
                hand2.setPower((gamepad1.right_trigger)*0.8);


                if (gamepad1.x && !xIsPressed){
                    xIsPressed = true;
                    if (lucifer.getPosition()==1 )
                        lucifer.setPosition(0);
                    else lucifer.setPosition(1);
                }
                else if (!gamepad1.x) xIsPressed = false;


                if (gamepad1.b && !BIsPressed){
                    BIsPressed = true;
                    if (servo1.getPosition()==1 )
                        servo1.setPosition(0);
                    else servo1.setPosition(1);
                }
                else if (!gamepad1.b) BIsPressed = false;


               // double leftPower = gamepad1.right_stick_y + gamepad1.right_stick_x;
              //  double rigthPower = gamepad1.right_stick_y - gamepad1.right_stick_x;



             //   if (leftPower>1||rigthPower>1){
             //       double maxPower = Math.max(leftPower,rigthPower);
              //      leftPower = leftPower/maxPower;
               //     rigthPower = rigthPower/maxPower;

                //  leftWheel.setPower(leftPower);
                //  rightWheel.setPower(rigthPower);

                if (gamepad1.left_trigger==0&&gamepad1.right_trigger==0);
                hand1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                if (gamepad1.left_trigger==0&&gamepad1.right_trigger==0);
                hand1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                }

            }
}



