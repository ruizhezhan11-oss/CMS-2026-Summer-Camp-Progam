package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;//這裡就像架設說明書，這樣後面才能照這個去寫
@TeleOp(name = "Sister")//永恆不變的手動程式基本架構
public class Sister extends LinearOpMode {  //extends是繼承的意思
    //這裡先宣告馬達
    private DcMotor BL;
    private DcMotor BR;
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor intake;
    private DcMotorEx shooter;
    private DcMotorEx shooter2;
    @Override
    public void runOpMode() {
        //這裡馬達的名稱要跟Driver Hub上的設定檔連結
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter2 = hardwareMap.get(DcMotorEx.class, "shooter2");
        //這裡設定馬達運轉的方向
        BL.setDirection(DcMotor.Direction.REVERSE); //Forward(正轉) or Reverse(反轉)
        BR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        if (opModeIsActive()) {
            //一次性程式，不太寫這邊
        while (opModeIsActive()) {
            //這裡面是Start之後的無限迴圈
            //搖桿的偵測寫這邊，有點像「重複無限次」的感覺

        }
        }
    }
}
