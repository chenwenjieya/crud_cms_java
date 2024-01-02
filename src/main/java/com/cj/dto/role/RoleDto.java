package com.cj.dto.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDto {
    @ApiModelProperty("角色名称")
    private String rolename;
    @ApiModelProperty("角色id")
    private Long id;
    @ApiModelProperty("备注")
    private String remark;
}
