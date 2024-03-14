package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Scanner;

@Autonomous
public class autonomousCamera extends LinearOpMode {


    final double TICKS_PER_REV = 537.7;
    final double RADIOS = 4.5;
    final double PERIMETER = 2 * RADIOS * Math.PI;
    final double GEAR_RATIO = 45.0 / 90.0;
    final double TICKS_REV_CM = (TICKS_PER_REV / GEAR_RATIO) / PERIMETER;
    final double WHEELS_DISTANCE = 31.5;
    final double CM_PER_DEGREE = ((WHEELS_DISTANCE * Math.PI) / 360) / 2;

    final int HEIGHT = 103;
    final int HEIGHT_OFF = 0;
    final int LEVEL_OF_ERROR = 100;
    final double ANGLE_ADAPTER = 1.97;
    final double AENGTH_ADAPTER = 1;

    DcMotor rightWheel;
    DcMotor leftWheel;
    DcMotor hand1;
    DcMotor hand2;
    Servo lucifer;
    Scanner camera1;

    @Override
    public void runOpMode() throws InterruptedException {

        rightWheel = hardwareMap.dcMotor.get("rightWheel");
        leftWheel = hardwareMap.dcMotor.get("leftWheel");
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

    }


}
