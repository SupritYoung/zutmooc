package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//用于封装注册
@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
