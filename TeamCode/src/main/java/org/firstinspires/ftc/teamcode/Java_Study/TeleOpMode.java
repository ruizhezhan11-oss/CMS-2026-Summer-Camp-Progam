package org.firstinspires.ftc.teamcode.Java_Study;
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
    DcMotorEx motor2;
    Servo servo ;

    @Override
    public void runOpMode()throws InterruptedException{
        waitForStart();
        while(opModeIsActive()){
        motor1.setPower(add(gamepad1.left_stick_x,gamepad1.left_stick_y));//呼叫函式
        }
    }
    //建立涵式
    public double add(double a,double b){
        return a+b;
    }
    public void init_hardware(){
        //!初始化硬體
        //Todo
    }

}
