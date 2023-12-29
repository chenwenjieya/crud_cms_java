package com.cj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cj.common.Result;
import com.cj.dto.UserQuery;
import com.cj.entity.User;
import com.cj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "用户管理", tags= "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 新增
    @ApiOperation(value = "新增用户", notes = "增加单个用户")
    @PostMapping("/addUser")
    public Result<String> addUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new Result<>().success("新增用户成功");
    }

    // 删除
    @ApiOperation(value = "删除", notes = "批量删除")
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Long> ids) {
        userService.removeByIds(ids);
        return new Result<>().success("删除用户成功");
    }

    // 修改
    @ApiOperation(value = "更新用户", notes = "更新用户")
    @PutMapping("/update")
    public Result<String> updateuser(@Valid @RequestBody User user) {
        userService.saveOrUpdate(user);
        return new Result<>().success("新增用户成功");
    }

    // 查询
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/selectPage")
    public Result<List<User>> findPage(@RequestBody UserQuery userQuery) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getCreateTime);

        // 模糊查询∂
        if(!userQuery.getUsername().isEmpty()) {
            wrapper.like(User::getUsername, userQuery.getUsername());
        }

        if (userQuery.getRoleId() != null) {
            wrapper.eq(User::getRoleId, userQuery.getRoleId());
        }



        Page<User> page = userService.page(
                new Page<>(
                        userQuery.getPageNum(),
                        userQuery.getPageSize()
                ),
                wrapper
        );

        return new Result<>().success(page.getRecords());
    }

}
