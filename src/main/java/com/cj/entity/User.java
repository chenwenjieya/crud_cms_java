package com.cj.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@TableName("user")
@Api(value = "用户管理", tags = "用户相关接口")
public class User {

    private Long id;

    @NotBlank(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号长度必须在2-20之间")
    @ApiModelProperty("账号")
    @TableField("user_name")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 2, max = 20, message = "密码长度必须在2-20之间")
    @ApiModelProperty("密码")
    @TableField("user_pwd")
    private String password;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;

    // 状态，0：禁用，1：启用
    @TableField("status")
    private Integer status;

}
