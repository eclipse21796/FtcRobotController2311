package nisayon1;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class driveing {/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
private DcMotor leftWheel;// מנוע שמאל
private DcMotor rightWheel;// מנוע ימין



    @TeleOp(name="drive", group="Linear OpMode")
//    @Disabled
    public class BasicOmniOpMode_Linear extends LinearOpMode {

        // Declare OpMode members for each of the 4 motors.

        @Override
        public void runOpMode() {
            leftWheel = hardwareMap.get(DcMotor.class,"leftWheel"); // השם של המנוע כמו שהוא מופיע בדרייבר האב
            rightWheel = hardwareMap.get(DcMotor.class,"rightWheel");
leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);//TODO  המנוע שהפוך leftWheel
            // Wait for the game to start (driver presses PLAY)
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();
            float[] movementVector = new float[] {0, 0};
            while (opModeIsActive()) { // one of the joystick sticks is moved
                // Forward-backward movement
                movementVector[0] = gamepad1.left_stick_y; // left wheel
                movementVector[1] = gamepad1.left_stick_y; // right wheel

                // צריך לבדוק איפה כיוון חיובי של stick X
                movementVector[0] += gamepad1.right_stick_x;
                movementVector[1] -= gamepad1.right_stick_x;

                leftWheel.setPower(movementVector[0]);
                rightWheel.setPower(movementVector[1]);
            }






            }
        }}


