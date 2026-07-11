package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "STAFF_AUTOTIME")
public class STAFF_AUTOTIME extends LinearOpMode{
    private enum State {
        DRIVE_FORWARD,DRIVE_REVERSE,STOP,RUN_RIGHTSIDE,RUN_LEFTSIDE,
        RUN_INTAKE,RUN_SHOOTER,RUN_SHOOTER2,RUN_INTAKE2,
    }//定義
    private DcMotor FR,FL,BR,BL;
    private DcMotor intake,intake2;
    private DcMotorEx shooter,shooter2;
    private final ElapsedTime MatchTimer = new ElapsedTime(30);
    private final ElapsedTime stateTimer = new ElapsedTime();
    private State state= State.DRIVE_FORWARD;
    @Override
    public void runOpMode(){
        FR = hardwareMap.get(DcMotor.class,"FR");
        FL = hardwareMap.get(DcMotor.class,"FL");
        BR = hardwareMap.get(DcMotor.class,"BR");
        BL = hardwareMap.get(DcMotor.class,"BL");
        intake = hardwareMap.get(DcMotor.class,"intake");
        intake2 = hardwareMap.get(DcMotor.class,"intake2");
        shooter = hardwareMap.get(DcMotorEx.class,"shooter");
        shooter2 = hardwareMap.get(DcMotorEx.class,"shooter2");
        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake2.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.REVERSE);
        shooter2.setDirection(DcMotor.Direction.REVERSE);

    }

}
