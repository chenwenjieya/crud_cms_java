package com.cj;

import com.cj.entity.Role;
import com.cj.mapper.RoleMapper;
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
        role.setId(1001L);
        role.setRolename("副校长");
        role.setRemark("临时的");
        roleMapper.insert(role);
    }

}
