package com.cj.dto.role;

import com.cj.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleQuery extends PageInfo {
    @ApiModelProperty("角色名称")
    private String rolename;
}
