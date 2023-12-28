package com.cj.service;

import com.cj.entity.Role;
import com.cj.entity.User;
import com.cj.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRole() {
        List<Role> roles = roleMapper.selectList(null);
        return roles;
    }

    public Role getRoleById(Long id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.selectById(id);
    }

}
