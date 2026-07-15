package org.firstinspires.ftc.teamcode.Java_Study;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="servo")
public class Servo_Example extends LinearOpMode {
    Servo motorservo;//Servo
    CRServo arm; //CRServo
    int servomode = 0;
    @Override
    public void runOpMode ()throws InterruptedException{
        init_hardware();
       waitForStart();
       while(opModeIsActive()){
           telemetry.addData("Data", arm.getPower());
           telemetry.addData("右板機",gamepad1.right_trigger);
           telemetry.update();
           motorservo.setDirection(Servo.Direction.FORWARD);
           arm.setDirection(DcMotorSimple.Direction.FORWARD);
           motorservo.setPosition(0);
            if(gamepad1.aWasPressed()){
                if(servomode==0){
                    servomode=1;
                    motorservo.setPosition(1);
                }else{
                    servomode=0;
                    motorservo.setPosition(0);
                }
            }
           arm.setPower(gamepad1.right_trigger);
       }

    }

    private void init_hardware(){
        motorservo = hardwareMap.get(Servo.class,"motorservo");
        arm=hardwareMap.get(CRServo.class,"arm");
    }
}
