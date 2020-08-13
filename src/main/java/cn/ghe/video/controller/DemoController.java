package cn.ghe.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("进入了.......");
        return "demo";
    }
}

