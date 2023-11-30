package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



    @TeleOp
    public class sofsofmasheu extends LinearOpMode {
        @Override
        public void runOpMode(){


            DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel");
            DcMotor leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
            DcMotor hand1 = hardwareMap.dcMotor.get("hand1" );

            leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();
            if(isStopRequested()){return;}
            resetRuntime();

            while (opModeIsActive()) {

                leftWheel.setPower(gamepad1.left_stick_y);
                rightWheel.setPower(gamepad1.left_stick_y);
                hand1.setPower((gamepad2.right_trigger));
                hand1.setPower((-gamepad2.left_trigger));


                double leftPower = gamepad1.left_stick_y + gamepad1.right_stick_x;
                double rigthPower = gamepad1.left_stick_y - gamepad1.right_stick_x;

                if (leftPower>1||rigthPower>1){
                    double maxPower = Math.max(leftPower,rigthPower);
                    leftPower = leftPower/maxPower;
                    rigthPower = rigthPower/maxPower;

                }
                leftWheel.setPower(leftPower);
                rightWheel.setPower(rigthPower);

                if (gamepad2.left_trigger==0&&gamepad2.right_trigger==0);
                    hand1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
}
    }


