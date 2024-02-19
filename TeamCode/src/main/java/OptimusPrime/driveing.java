package OptimusPrime;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class driveing {

private DcMotor leftWheel;// מנוע שמאל
private DcMotor rightWheel;// מנוע ימין
private Servo elevator; // מעלית

    public class BasicOmniOpMode_Linear extends LinearOpMode {


       // @Override
        public void runOpMode() {
            leftWheel = hardwareMap.get(DcMotor.class,"leftWheel"); // השם של המנוע כמו שהוא מופיע בדרייבר האב
            rightWheel = hardwareMap.get(DcMotor.class,"rightWheel");
            elevator = hardwareMap.get(Servo.class,"elevator");

            leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);//TODO  המנוע שהפוך leftWheel

            telemetry.addData("Status", "Initialized");
            telemetry.update();
            float[] movementVector = new float[] {0, 0};
            waitForStart();
            if (isStopRequested()) return;
            resetRuntime(); 
            while (opModeIsActive()) {
                leftWheel.setPower(gamepad1.left_stick_y);
                rightWheel.setPower(gamepad1.left_stick_y);
                movementVector[0] = gamepad1.right_stick_y; // left wheel
                movementVector[1] = gamepad1.right_stick_y; // right wheel

                // צריך לבדוק איפה כיוון חיובי של stick X
                movementVector[0] += gamepad1.left_stick_x;
                movementVector[1] -= gamepad1.left_stick_x;

                leftWheel.setPower(movementVector[0]);
                rightWheel.setPower(movementVector[1]);
            }






            }
        }}


