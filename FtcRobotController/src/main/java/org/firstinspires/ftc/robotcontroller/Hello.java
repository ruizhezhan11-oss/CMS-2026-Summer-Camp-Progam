package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Hello World", group="Iterative OpMode")
public class Hello extends OpMode {

    @Override
    public  void init() {
        telemetry.addData("Hello","World");
    }

    @Override
    public void loop() {

    }
}
