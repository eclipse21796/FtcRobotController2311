package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveTrain extends LinearOpMode {
    @Override
    public void runOpMode() {

        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // endregion

        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();
        // endregion

        while (opModeIsActive()) {
            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)

            //                      [      straight       ] [       lateral       ] [         turn         ]
            double frontRightPower = gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x;
            double frontLeftPower = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
            double backRightPower = gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x;
            double backLeftPower = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
            // endregion

            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double highestAbsoluteNum = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));

            // if the motors aren't capable of the power requirement
            if (highestAbsoluteNum > 1) {
                // normalize the motor powers to be between -1 to 1
                frontRightPower /= highestAbsoluteNum;
                frontLeftPower /= highestAbsoluteNum;
                backRightPower /= highestAbsoluteNum;
                backLeftPower /= highestAbsoluteNum;
            }
            // endregion

            // region SET MOTOR POWER
            frontRight.setPower(frontRightPower);
            frontLeft.setPower(frontLeftPower);
            backRight.setPower(backRightPower);
            backLeft.setPower(backLeftPower);
            // endregion

        }
    }
}
