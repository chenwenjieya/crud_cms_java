package com.cj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cj.common.Result;
import com.cj.dto.RoleQuery;
import com.cj.entity.Role;
import com.cj.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(value = "角色管理", tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    // 分页查询
    @PostMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Role>> finPage(@RequestBody RoleQuery roleQuery) {

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
        return new Result<>().success(page.getRecords());
    }


    // 编辑或者新增  id
    @PostMapping("/save")
    @ApiOperation(value = "编辑或者新增", notes = "编辑或者新增")
    public Result<String> editOrAdd(@Valid @RequestBody Role role) {

//        throw new CustomException(502,"自定义异常");

        roleService.saveOrUpdate(role);
        return new Result<>().success("新增/编辑成功");
    }


    // 批量删除
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除", notes = "批量删除")
    public Result<String> delete(@RequestBody List<Long> ids) {
        roleService.removeByIds(ids);
        return new Result<>().success("删除成功");
    }

}
