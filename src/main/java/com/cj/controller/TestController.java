package com.cj.controller;

import com.cj.entity.Role;
import com.cj.entity.User;
import com.cj.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${text}")
    private String text;

    @Autowired
    private RoleService roleService;

    @GetMapping
    @ApiOperation(value = "测试接口", notes = "测试")
    public String Test() {
        return "Hello World! HELLOW";
    }

    @GetMapping("/env")
    @ApiOperation(value = "环境接口", notes = "当前运行环境")
    public String testEnv() {
        return text;
    }

    @GetMapping("/role")
    @ApiOperation(value = "用户", notes = "获取所有的用户信息")
    public List<Role> getRole() {
        return roleService.getRole();
    }

    // 根据角色id返回角色信息
    @GetMapping("/role/info")
    @ApiOperation(value = "角色信息", notes = "根据角色id返回角色信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value= "角色id", required = true, dataType = "Long", paramType = "query", defaultValue = "")
            }
    )
    public Role getRoleById(@RequestParam("id") Long id) {
        return roleService.getRoleById(id);
    }

}
