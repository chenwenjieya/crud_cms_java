package com.cj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT * FROM role WHERE id = #{roleId}")
    Role selectOneRoleByUser(Long roleId);

    @Select("SELECT * FROM role WHERE id = #{roleId}")
    @Results(
            id = "selectRoleOne",
            value = {
                    @Result(property = "id", column = "id", id = true),
                    @Result(property = "rolename", column = "role_name"),
                    @Result(property = "remark", column = "remark"),
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "deleted", column = "deleted"),
                    @Result(property = "userList", column = "id", javaType = List.class, many = @Many(select = "com.cj.mapper.UserMapper.selectUserByRole", fetchType = FetchType.LAZY)),
            }
    )
    Role selectRole(Long roleId);


}
