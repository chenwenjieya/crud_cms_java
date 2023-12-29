package com.cj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserLogin {
    @NotBlank(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号长度必须在2-20之间")
    @ApiModelProperty("账号")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 2, max = 20, message = "密码长度必须在2-20之间")
    @ApiModelProperty("密码")
    private String password;

}
