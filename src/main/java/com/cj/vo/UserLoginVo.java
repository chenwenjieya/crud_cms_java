package com.cj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登陆成功的返回值
 */
@Data
public class UserLoginVo {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("角色id")
    private Long roleId;
}
