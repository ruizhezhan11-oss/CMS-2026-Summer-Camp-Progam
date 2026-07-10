package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;//計時器
//==================================================================================//
@TeleOp(name = "STAFF_FINAL")
public class STAFF_FINAL extends LinearOpMode {
    private void init_hardware(){
        DcMotor BL, BR, FL, FR;
        DcMotor intake, intake2;
        DcMotorEx shooter, shooter2;
    }
    DcMotor BL;
    DcMotor BR;
    DcMotor FL;
    DcMotor FR;
    DcMotor intake;
    DcMotor intake2;
    DcMotorEx shooter;
    DcMotorEx shooter2;


    //以下三個變數常用
    double targetRPM = 3000;//Shooter固定參數
    double CPR = 28;//Shooter固定參數
    boolean autoFireOn = false;//自動需要
    double intake1Seconds = 4;
    ElapsedTime spinUpTimer = new ElapsedTime();//自動程序計時Timer

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
        //hardwareMap.get
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
        intake2.setDirection(DcMotor.Direction.FORWARD);
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
        waitForStart();//Init Finish
        //Press "START"
        if (opModeIsActive()) {
            // 一次性程式
            telemetry.addLine("成功啟動OpMode");
            //======無限迴圈======\\
            while (opModeIsActive()) {
                // While迴圈
                //全向移動
                FL.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
                FR.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x);
                BL.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x) + gamepad1.right_stick_x);
                BR.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x) - gamepad1.right_stick_x);

                //自動吸球射球 (仿製 程式King 【team_1_final.java】)
                //一勞永逸，所有按鈕功能都在這
                //開關
                if (gamepad1.aWasPressed()) {
                    if (autoFireOn) {
                        autoFireOn = false;   // 已經在連招中，再按一次直接關
                    } else {
                        autoFireOn = true;    // 還沒開始，按下去啟動
                        spinUpTimer.reset();  // 重置計時器
                    }
                }

                if(autoFireOn){
                    //自動程序
                    //先等待吸球
                    intake.setPower(1);
                    intake2.setPower(1);
                    //
                    if (spinUpTimer.seconds() >= intake1Seconds) {
                        intake.setPower(0);
                        intake2.setPower(1);
                        double ticksPerSecond = (targetRPM / 60.0) * CPR;
                        shooter.setVelocity(ticksPerSecond);
                        shooter2.setVelocity(ticksPerSecond);}


                } else{
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
                        shooter.setVelocity(0);
                    }
                    //按X強制停止
                    if (gamepad1.x) {
                        autoFireOn = false;
                        stopAllMotors();

                    }
                }
                updateTelemetry();
                telemetry.update();

            }


        }
    }
    private void updateTelemetry(){
        double shooter1RPM = (shooter.getVelocity() / CPR) * 60.0;
        double shooter2RPM = (shooter2.getVelocity() / CPR) * 60.0;
        telemetry.addLine("Shooter一律進戰模式:3750轉");
        telemetry.addData("Shooter1即時轉速", shooter1RPM);
        telemetry.addData("Shooter2及時轉速", shooter2RPM);
        telemetry.addData("自動連招模式", autoFireOn ? "🟢 開啟" : "⚪ 關閉");
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
