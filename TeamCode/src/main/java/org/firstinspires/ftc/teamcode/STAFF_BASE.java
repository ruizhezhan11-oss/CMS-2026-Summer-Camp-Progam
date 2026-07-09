package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
public abstract class STAFF_BASE extends LinearOpMode {
    public void init_hardware(){
        idle();
        DcMotor BL;
        DcMotor BR;
        DcMotor FL;
        DcMotor FR;
        DcMotor intake;
        DcMotor intake2;
        DcMotorEx shooter;
        DcMotorEx shooter2;
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");

    }
}
