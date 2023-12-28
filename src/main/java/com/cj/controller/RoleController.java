package com.cj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cj.common.Result;
import com.cj.dto.RoleQuery;
import com.cj.entity.Role;
import com.cj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    // 分页查询
    @PostMapping("/page")
    public Result<?> finPage(@RequestBody RoleQuery roleQuery) {

        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Role::getCreateTime);

        // 模糊查询
        if (!roleQuery.getRolename().isEmpty()) {
            wrapper.like(Role::getRolename, roleQuery.getRolename());
        }

        Page<Role> page = roleService.page(
                new Page<>(
                        roleQuery.getPageNum(),
                        roleQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page.getRecords());
    }

}
