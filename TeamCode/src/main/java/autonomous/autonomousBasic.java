package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class autonomousBasic extends LinearOpMode {


    final int TICKS_PER_REV = 560;
    final double RADIOS = 4.5;
    final double PERIMETER = 2*RADIOS*Math.PI;
    final double GEAR_RATIO = 45.0/90.0;
    final double TICKS_REV_CM = (TICKS_PER_REV/GEAR_RATIO)/PERIMETER;
    final double WHEELS_DISTANCE = 10; // TODO: לבדוק בסרגל מרחק של הגלגלים
    final double CM_PER_DEGREE = ((WHEELS_DISTANCE*Math.PI)/360)/2;
    final int HEIGHT = -100;
    final int HEIGHT_OFF = 0;

    DcMotor rightWheel;
    DcMotor leftWheel;
    DcMotor hand1;
    Servo lucifer;


    @Override
    public void runOpMode() throws InterruptedException {

        rightWheel = hardwareMap.dcMotor.get("rightWheel");
        leftWheel  = hardwareMap.dcMotor.get("leftWheel" );
        hand1 = hardwareMap.dcMotor.get("hand1");
        lucifer = hardwareMap.servo.get("lucifer");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hand1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hand1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftWheel.setTargetPosition(0);
        rightWheel.setTargetPosition(0);
        hand1.setTargetPosition(0);

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();


        while (opModeIsActive()){
          driveY(10,1);
          turn(90,0.5);
          raiseHand1();
          driveY(5,1);
          raiseHandOff();
        }
    }

    public void driveY (double cm,double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);

        leftWheel.setTargetPosition(leftWheel.getTargetPosition()+(int) Math.round(cm*TICKS_REV_CM));
        rightWheel.setTargetPosition(rightWheel.getTargetPosition()+(int) Math.round(cm*TICKS_REV_CM));

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void turn (double degree,double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);

        leftWheel.setTargetPosition(leftWheel.getTargetPosition()-(int) Math.round(degree*CM_PER_DEGREE*TICKS_REV_CM));
        rightWheel.setTargetPosition(rightWheel.getTargetPosition()+(int) Math.round(degree*CM_PER_DEGREE*TICKS_REV_CM));

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void raiseHand1 (){
        hand1.setPower(0.5);
        hand1.setTargetPosition(HEIGHT);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void raiseHandOff (){
        hand1.setPower(0.5);
        hand1.setTargetPosition(HEIGHT_OFF);
        hand1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}

