package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "STAFF_JAVA")
public class STAFF extends LinearOpMode {


    private DcMotor BL;
    private DcMotor BR;
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor intake;
    private DcMotor intake2;
    private DcMotorEx shooter;
    private DcMotorEx shooter2;
    //以下三個變數常用
    double targetRPM = 3800;//Shooter固定參數
    double CPR = 28;//Shooter固定參數
    boolean autoFireOn = false;//自動需要

    /**
     * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
     * Stopped).
     */
    //配置:詳見工筆
    //這程式應該沒人會來看，除了驗收的學長
    @Override
    public void runOpMode() {
        telemetry.addLine("歡迎使用工作人員機器人" );
        telemetry.addLine("The Robot is designed by CMS FTC STAFF");
        telemetry.addLine("詳細操作及幕後人員請參閱工作人員隊伍工筆" );
        //註冊

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter2 = hardwareMap.get(DcMotorEx.class, "shooter2");

        //底盤轉向
        BL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        //底盤煞車系統
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //吸吐球系統轉向
        intake.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.FORWARD);
        shooter2.setDirection(DcMotor.Direction.REVERSE);
        //設定模式
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//請加裝Encoder
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//請加裝Encoder



        // Init結束
        waitForStart();
        //Press "START"
        if (opModeIsActive()) {
            // 一次性程式
            while (opModeIsActive()) {
                // While迴圈
                FL.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
                FR.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
                BL.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) + gamepad1.right_stick_x);
                BR.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x) - gamepad1.right_stick_x);

                //自動吸球射球 (仿製 程式King 【team_1_final.java】)
                //一勞永逸，所有按鈕功能都在這
                //開關
                if (gamepad1.rightBumperWasPressed()) {
                    autoFireOn = !autoFireOn;}

                if(autoFireOn){
                    double ticksPerSecond = (targetRPM/ 60.0) * CPR;
                    shooter2.setVelocity(ticksPerSecond);
                    shooter.setVelocity(ticksPerSecond);

                    intake.setPower(1);
                    intake2.setPower(1);
                    shooter.setVelocity(ticksPerSecond);
                    shooter2.setVelocity(ticksPerSecond);

                }
                else{
                    //Intake
                    if (gamepad1.a) {
                        intake.setPower(1);
                    } else {
                        intake.setPower(0);
                    }

                    //Intake2
                    if(gamepad1.y){
                        intake2.setPower(1);

                    }else{
                        intake2.setPower(0);
                    }
                    //Shooter1
                    if(gamepad1.b){
                        double ticksPerSecond = (targetRPM / 60.0) * CPR;
                        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        shooter.setVelocity(ticksPerSecond);
                        double externalGearRatio = 2.0; //無減速箱


                    }else {
                        shooter.setVelocity(0);

                    }

                    //shooter2
                    if(gamepad1.b){
                        double ticksPerSecond = (targetRPM / 60.0) * CPR;
                        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        shooter2.setVelocity(ticksPerSecond);

                    }else{
                        shooter2.setVelocity(0);
                    }


                }

                //按X強制停止
                if (gamepad1.x) {
                    autoFireOn = false;
                    shooter.setVelocity(0);
                    shooter2.setVelocity(0);
                    intake.setPower(0);
                    intake2.setPower(0);

                }
                double Shooter2RPM=shooter2.getVelocity();
                double Shooter1RPM=shooter.getVelocity();

                telemetry.addData("Shooter1即時轉速",Shooter1RPM);
                telemetry.addData("Shooter2及時轉速",Shooter2RPM);
                telemetry.addData("自動連招模式", autoFireOn ? "🟢 開啟" : "⚪ 關閉");
                }


            }
        }
    /// Stop all motors
    private void stopAllMotors() {
        BL.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        intake.setPower(0);
        intake2.setPower(0);
        shooter.setVelocity(0);
        shooter2.setVelocity(0);
    } }
