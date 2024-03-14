package autonomous;

import android.graphics.Color;
import android.provider.OpenableColumns;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class cameraTest {

    OpenCvWebcam camera;
    imageProcessorPipeline pipeline;



    public cameraTest(HardwareMap map,LinearOpMode opMode){
        camera = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class,"camera1"));
        pipeline = new imageProcessorPipeline(opMode);
        camera.setPipeline(pipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }


 public static class imageProcessorPipeline extends OpenCvPipeline{

        //נקודה ברזולוציה
        static final Point CENTER_TOP_LEFT = new Point(915,320);
        static final Point CENTER_BOTTOM_RIGHT = new Point(1025,420);
        static final Point RIGHT_TOP_LEFT = new Point(425,295);
        static final Point RIGHT_BOTTOM_RIGHT = new Point(610,405);
        static final Scalar RED = new Scalar(245,0,0); //TODO המספר הסידורי של הפרופ

        Mat YCrCb = new Mat();
        Mat Cb = new Mat();

        Mat leftCb;
        Mat centerCb;

        int centerAvg,leftAvg;

        LinearOpMode opMode;




     public enum Position{
         LEFT,RIGHT,CENTER
     }



     @Override
     public void init(Mat frame){

         Imgproc.cvtColor(frame,YCrCb,Imgproc.COLOR_RGB2YCrCb);
         Core.extractChannel(YCrCb,Cb,0);

         leftCb = Cb.submat(new Rect(RIGHT_TOP_LEFT,RIGHT_BOTTOM_RIGHT));
         centerCb = Cb.submat(new Rect(CENTER_TOP_LEFT,CENTER_BOTTOM_RIGHT));

     }

     @Override
     public Mat processFrame(Mat input) {

         Imgproc.cvtColor(input,YCrCb,Imgproc.COLOR_RGB2YCrCb);
         Core.extractChannel(YCrCb,Cb,0);

         leftAvg = (int) Core.mean(leftCb).val[0];
         centerAvg = (int) Core.mean(centerCb).val[0];

         Imgproc.rectangle(input,RIGHT_TOP_LEFT,RIGHT_BOTTOM_RIGHT,RED,15);
         Imgproc.rectangle(input,CENTER_TOP_LEFT,CENTER_BOTTOM_RIGHT,RED,15);

         opMode.telemetry.addData("left",leftAvg);
         opMode.telemetry.addData("center",centerAvg);
         opMode.telemetry.addData("position",position());
         opMode.telemetry.update();

         return input;
     }
     public Position position(){

         if (leftAvg > centerAvg && leftAvg > 115){
             return Position.LEFT;

         }
        else if (centerAvg > 115){
            return Position.CENTER;
         }
        else {
            return Position.RIGHT;
        }
     }

     public imageProcessorPipeline(LinearOpMode O){
         opMode = O;
     }


 }

}



































