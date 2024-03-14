package OptimusPrime; // השם של התיקיה

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp // מופיע ב driver hub בתוכנית TeleOp
    public class sofsofmasheu extends LinearOpMode { // השם של הclass כפי שהוא מופיע בשורת התוכנית
    @Override // מופיע ב driver hub
    public void runOpMode() {

        boolean isDpadUpPressed = false;

        DcMotor rightWheel = hardwareMap.dcMotor.get("rightWheel"); // גלגל ימני
        DcMotor leftWheel = hardwareMap.dcMotor.get("leftWheel"); // גלגל שמאלי
        DcMotor hand1 = hardwareMap.dcMotor.get("hand1"); // זרוע
        DcMotor hand2 = hardwareMap.dcMotor.get("hand2"); // כיוונון זרוע
        Servo lucifer = hardwareMap.servo.get("lucifer"); // תופסן פיקסלים
        Servo servo1 = hardwareMap.servo.get("servo1"); // כיוונון תופסן
        Servo paperdrone = hardwareMap.servo.get("paperdrone"); // מטוס

        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE); // המנוע הימני מופעל ברוורס
        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD); // המנוע השמאלי מופעל בכיוון השעון
        hand1.setDirection(DcMotorSimple.Direction.FORWARD);
        hand2.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart(); // תחכה ל INIT בשביל להריץ את התוכנה
        if (isStopRequested()) {
            return;
        }
        resetRuntime();



        while (opModeIsActive()) { // כל עוד התוכנה רצה, תפעיל:

            leftWheel.setPower(gamepad1.right_stick_y);  // המנוע עובד לפי הכוח של gamepad1.right_stick_y
            rightWheel.setPower(gamepad1.left_stick_y); // המנוע עובד לפי הכוח של gamepad1.left_stick_y


            if(!isDpadUpPressed){
                hand1.setPower((-gamepad1.right_trigger)); // המנוע עובד לפי הכוח של -gamepad1.right_trigger
                hand1.setPower((gamepad1.left_trigger));// המנוע עובד לפי הכוח של gamepad1.left_trigger
                hand1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // המנוע עוצר איפה שהוא

                hand2.setPower((-gamepad1.right_trigger)); //המנוע עובד לפי הכוח של -gamepad1.right_trigger
                hand2.setPower((gamepad1.left_trigger)); //המנוע עובד לפי הכוח של gamepad1.left_trigger
                hand2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // המנוע עוצר איפה שהוא
            }

            if (gamepad1.dpad_up){
                isDpadUpPressed = true;
                hand1.setPower(1);
                hand2.setPower(1);
            }


            if (gamepad1.x){ // כש gamepad1.x לחוץ
                lucifer.setPosition(1); // תניע את הסרבו לנקודה 1
            }
            if (gamepad1.b) { //כש gamepad1.b לחוץ
                lucifer.setPosition(0); // תניע את הסרבו לנקודה 0
            }
            if (gamepad1.y) { // כש gamepad1.Y לחוץ
                paperdrone.setPosition(1); //תניע את הסרבו לנקודה 0

            }

        }

    }
}



