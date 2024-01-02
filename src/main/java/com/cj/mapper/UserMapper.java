package com.cj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.entity.Role;
import com.cj.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id = #{id}")
    @Results(
            id = "selectUserOne",
            value = {
                    @Result(property = "id", column = "id", id = true),
                    @Result(property = "username", column = "user_name"),
                    @Result(property = "password", column = "user_pwd"),
                    @Result(property = "roleId", column = "role_id"),
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "deleted", column = "deleted"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "role", column = "role_id", javaType = Role.class, one = @One(select = "com.cj.mapper.RoleMapper.selectOneRoleByUser", fetchType = FetchType.LAZY)),
            }
    )
    User getUserOne(@Param("id") Long id);

    @Select("select * from user where role_id = #{roleId}")
    User selectUserByRole(Long roleId);

}
