package com.cj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT * FROM role WHERE id = #{roleId}")
    Role selectOneRoleByUser(Long roleId);
}
