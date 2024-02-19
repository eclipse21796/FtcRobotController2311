package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class autonomousREDBasic extends LinearOpMode {


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

        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        hand1.setDirection(DcMotorSimple.Direction.FORWARD);
        hand2.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hand2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftWheel.setTargetPosition(0);
        rightWheel.setTargetPosition(0);
        hand1.setTargetPosition(0);
        hand2.setTargetPosition(0);

        leftWheel.setPower(1);
        rightWheel.setPower(1);

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        driveY(-80,1);
        // turn(360,0.5);
        // raiseHand1();
        //driveY(5,1);
        // raiseHandOff();
        // releasePixel();


        while (opModeIsActive()){

        }
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
                opModeIsActive()){}
        rightWheel.setPower(0);
        leftWheel.setPower(0);
    }

    public void turn (double degree,double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);

        leftWheel.setTargetPosition(leftWheel.getTargetPosition()-(int) Math.round(degree*CM_PER_DEGREE*TICKS_REV_CM * ANGLE_ADAPTER));
        rightWheel.setTargetPosition(rightWheel.getTargetPosition()+(int) Math.round(degree*CM_PER_DEGREE*TICKS_REV_CM * ANGLE_ADAPTER));

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(Math.abs(rightWheel.getCurrentPosition()-rightWheel.getTargetPosition()) > LEVEL_OF_ERROR &&
                Math.abs(leftWheel.getCurrentPosition()-leftWheel.getTargetPosition()) > LEVEL_OF_ERROR &&
                opModeIsActive()){}

    }
    public void raiseHand1 (){
        hand1.setPower(0.5);
        hand1.setTargetPosition(HEIGHT);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand2.setPower(0.5);
        hand2.setTargetPosition(HEIGHT);
        hand2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void raiseHandOff (){
        hand1.setPower(0.5);
        hand1.setTargetPosition(HEIGHT_OFF);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand2.setPower(0.5);
        hand2.setTargetPosition(HEIGHT_OFF);
        hand2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void releasePixel (){
        lucifer.setPosition(0);



    }
}


