package com.cj;

import com.cj.entity.Role;
import com.cj.entity.User;
import com.cj.mapper.RoleMapper;
import com.cj.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProjectApplicationTests {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void test() {
        Role role = new Role();
        role.setId(1000L);
        role.setRolename("校长");
        roleMapper.insert(role);
    }

}
