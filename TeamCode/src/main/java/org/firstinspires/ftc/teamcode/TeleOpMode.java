package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;//直線程式
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TeleOpMode")
public class TeleOpMode extends LinearOpMode {
    //Init
    boolean isButtonAPressed = false;
    int Sixseven = 67;
    double abc = 3.14159;
    DcMotor motor1 ;
    DcMotorEx arm;
    Servo servo ;

    @Override
    public void runOpMode()throws InterruptedException{
        init_hardware();
        waitForStart();
        while(opModeIsActive()){
        motor1.setPower(add(gamepad1.left_stick_x,gamepad1.left_stick_y));//呼叫函式
            if(gamepad1.a){
                motor1.setPower(1);
            }
            if(gamepad1.b){
                arm.setTargetPosition(12000);
                arm.setPower(0.6);
            }else{
                arm.setPower(0);
            }

        }
    }
    //建立涵式
    private double add(double a,double b){
        return a+b;
    }
    private void init_hardware(){
        motor1 = hardwareMap.get(DcMotor.class,"motor1");
        arm = hardwareMap.get(DcMotorEx.class,"arm");
        servo = hardwareMap.get(Servo.class,"servo");
        motor1.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.REVERSE);
        servo.setDirection(Servo.Direction.FORWARD);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
