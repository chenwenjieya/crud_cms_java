package com.cj;

import com.cj.common.JwtUtil;
import com.cj.entity.Role;
import com.cj.entity.User;
import com.cj.mapper.RoleMapper;
import io.jsonwebtoken.Claims;
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

    @Test
    public void testJwt() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ceshi token");
        String s = JwtUtil.generateToken(user);
        //eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImNlc2hpIHRva2VuIiwiZXhwIjoxNzAzODQ3Njk4LCJ1c2VySWQiOjEsImlhdCI6MTcwMzg0NTEwNiwianRpIjoidG9rZW5JZCJ9.RN2umVnHbOIblf6HfvELpTJq-enhIgAx2KAwQp3jRcU
        System.out.println(s);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImNlc2hpIHRva2VuIiwiZXhwIjoxNzAzODQ3Njk4LCJ1c2VySWQiOjEsImlhdCI6MTcwMzg0NTEwNiwianRpIjoidG9rZW5JZCJ9.RN2umVnHbOIblf6HfvELpTJq-enhIgAx2KAwQp3jRcU";
        // 验证jwt
        Claims claims = JwtUtil.verifyJwt(token);

        System.out.println(claims.get("userId"));
        System.out.println(claims.get("userName"));

        System.out.println(claims.get("id"));
    }

}
