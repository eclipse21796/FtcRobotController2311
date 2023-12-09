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
            Servo lucifer = hardwareMap.servo.get("lucifer"); // TODO: להוסיף לקונפיגורציה בדרייבר האב

            rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();
            if(isStopRequested()){return;}
            resetRuntime();

            boolean xIsPressed = false;

            while (opModeIsActive()) {

                leftWheel.setPower(gamepad1.right_stick_y);
                rightWheel.setPower(gamepad1.right_stick_y);
                hand1.setPower((gamepad1.right_trigger));
                hand1.setPower((-gamepad1.left_trigger));

                if (gamepad1.x && !xIsPressed){
                    xIsPressed = true;
                    if (lucifer.getPosition()==1 )
                        lucifer.setPosition(0);
                    else lucifer.setPosition(1);
                }
                else if (!gamepad1.x) xIsPressed = false;

                double leftPower = gamepad1.right_stick_y + gamepad1.left_stick_x;
                double rigthPower = gamepad1.right_stick_y - gamepad1.left_stick_x;

                if (leftPower>1||rigthPower>1){
                    double maxPower = Math.max(leftPower,rigthPower);
                    leftPower = leftPower/maxPower;
                    rigthPower = rigthPower/maxPower;

                }
                leftWheel.setPower(leftPower);
                rightWheel.setPower(rigthPower);

                if (gamepad1.left_trigger==0&&gamepad1.right_trigger==0);
                    hand1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
}
    }


