package com.cj.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto extends UserLogin{

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("账号状态")
    private Integer status;

    @ApiModelProperty("用户id")
    private Long id;

}
