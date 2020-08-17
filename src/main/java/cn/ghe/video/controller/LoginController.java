package cn.ghe.video.controller;

import cn.ghe.video.bean.Rest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Api(value="登陆",tags="登陆模块")
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登陆" , notes = "用户登陆")
    public String index() {
        Rest mv = new Rest();
        mv.setStatus("ok");
        return "成功";

    }
}
