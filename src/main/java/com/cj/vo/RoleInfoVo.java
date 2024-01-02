package com.cj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleInfoVo {
    @ApiModelProperty("角色名")
    private String rolename;

    @ApiModelProperty("角色id")
    private Long roleId;
}
