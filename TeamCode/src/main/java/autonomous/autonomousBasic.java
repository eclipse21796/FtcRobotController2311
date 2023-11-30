package autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class autonomousBasic extends LinearOpMode {


    final int TICKS_PER_REV = 560;
    final double RADIOS = 4.5;
    final double PERIMETER = 2*RADIOS*Math.PI;

    final double TICKS_REV_CM = TICKS_PER_REV/PERIMETER;


    DcMotor rightWheel;
    DcMotor leftWheel;



    @Override
    public void runOpMode() throws InterruptedException {

        rightWheel = hardwareMap.dcMotor.get("rightWheel");
        leftWheel  = hardwareMap.dcMotor.get("leftWheel" );

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        leftWheel.setTargetPosition(0);
//        rightWheel.setTargetPosition(0);
//
//        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();



//        driveY(50,1);

        while (opModeIsActive()){
        }
    }

    public void driveY (double cm,double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);

        telemetry.addData("fff", (int) Math.round(cm*TICKS_REV_CM));
        telemetry.update();
        leftWheel.setTargetPosition((int) Math.round(cm*TICKS_REV_CM));
        rightWheel.setTargetPosition((int) Math.round(cm*TICKS_REV_CM));

        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
