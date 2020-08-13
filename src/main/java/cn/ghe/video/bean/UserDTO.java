package cn.ghe.video.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserDTO {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "用户登录账号", required = true)
    private String userNo;
    @ApiModelProperty(value = "姓名", required = true)
    private String userName;
    @ApiModelProperty(value = "姓名拼音")
    private String spellName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "手机号", required = true)
    private String userPhone;
    @ApiModelProperty(value = "性别")
    private Integer userGender;
    @ApiModelProperty(value = "记录创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "记录修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
