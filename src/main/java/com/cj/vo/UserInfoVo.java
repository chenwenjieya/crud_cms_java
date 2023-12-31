package com.cj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户和角色信息返回值
 */
@Data
public class UserInfoVo {
    @ApiModelProperty("角色列表")
    private RoleInfoVo roleInfo;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    // 状态，0：禁用，1：启用
    @ApiModelProperty("账号状态，0：禁用，1：启用")
    private Integer status;

}
