package cn.ghe.video.controller;

import cn.ghe.video.bean.LoginDO;
import cn.ghe.video.bean.Rest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(value="登陆",tags="登陆模块")
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    @ApiImplicitParam(name = "LoginDO",value = "测试",required = true,dataType = "LoginDO")
    @ApiOperation(value = "登陆" , notes = "用户登陆")
    public Rest index(@RequestBody LoginDO loginDo) {
        System.out.println("用户登陆--------------------------------");
        Rest mv = new Rest();
        if("admin".equals(loginDo.getUserName())){
            mv.setStatus("ok");
            mv.setSuccess(true);
            mv.setMsg("登录成功!");
            mv.setCurrentAuthority("admin");
        }else{
            mv.setStatus("error");
            mv.setSuccess(false);
            mv.setMsg("登录失败!");
            mv.setCurrentAuthority("guest");
        }
        return mv;

    }
}
