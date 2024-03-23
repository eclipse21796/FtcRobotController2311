package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class autonomouscameraBLUETEST extends LinearOpMode {


    final double TICKS_PER_REV = 537.7;
    final double RADIOS = 4.5;
    final double PERIMETER = 2*RADIOS*Math.PI;
    final double GEAR_RATIO = 45.0/90.0;
    final double TICKS_REV_CM = (TICKS_PER_REV/GEAR_RATIO)/PERIMETER;
    final double WHEELS_DISTANCE = 31.5;
    final double CM_PER_DEGREE = ((WHEELS_DISTANCE*Math.PI)/360)/2;

    final int HEIGHT = 103;
    final int HEIGHT_OFF = 0;
    final int LEVEL_OF_ERROR = 100;
    final double ANGLE_ADAPTER =1.97;
    final double AENGTH_ADAPTER = 1;

    DcMotor rightWheel;
    DcMotor leftWheel;
    DcMotor hand1;
    DcMotor hand2;
    Servo lucifer;

    @Override
    public void runOpMode() throws InterruptedException {

        rightWheel = hardwareMap.dcMotor.get("rightWheel");
        leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
        hand1 = hardwareMap.dcMotor.get("hand1");
        hand2 = hardwareMap.dcMotor.get("hand2");
        lucifer = hardwareMap.servo.get("lucifer");

        rightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        hand1.setDirection(DcMotorSimple.Direction.FORWARD);
        hand2.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hand2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hand1.setTargetPosition(0);
        hand1.setTargetPosition(0);
        rightWheel.setTargetPosition(0);
        leftWheel.setTargetPosition(0);

        leftWheel.setPower(0);
        rightWheel.setPower(0);

        cameraTest camera = new cameraTest(hardwareMap,this);

        waitForStart();

        rightWheel.setTargetPosition(-3077);
        //leftWheel.setTargetPosition(-3074);

        //leftWheel.setPower(0.5);
        rightWheel.setPower(-0.5);

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive()){

            telemetry.addData( "right",rightWheel.getCurrentPosition());
            telemetry.addData( "left",leftWheel.getCurrentPosition());
            telemetry.update();
        }
        //driveY(-50,0.5);

    }

    public void driveY (double cm,double power){
        leftWheel.setPower((power));
        rightWheel.setPower((power));

        leftWheel.setTargetPosition(leftWheel.getTargetPosition()+(int) Math.round(cm*TICKS_REV_CM * AENGTH_ADAPTER));
        rightWheel.setTargetPosition(rightWheel.getTargetPosition()+(int) Math.round(cm*TICKS_REV_CM * AENGTH_ADAPTER));

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(Math.abs(rightWheel.getCurrentPosition()-rightWheel.getTargetPosition()) > LEVEL_OF_ERROR &&
                Math.abs(leftWheel.getCurrentPosition()-leftWheel.getTargetPosition()) > LEVEL_OF_ERROR &&

                opModeIsActive()){

        }
        rightWheel.setPower(0);
        leftWheel.setPower(0);
    }

}

