package com.cj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cj.common.PageResult;
import com.cj.common.Result;
import com.cj.dto.role.RoleDto;
import com.cj.dto.role.RoleQuery;
import com.cj.entity.Role;
import com.cj.exception.CustomException;
import com.cj.mapper.RoleMapper;
import com.cj.service.impl.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private RoleMapper roleMapper;


    // 分页查询
    @PostMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public PageResult<List<Role>> finPage(@RequestBody RoleQuery roleQuery) {

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


        System.out.println(page.getRecords());
        return new PageResult<>().success(page.getRecords(), page.getTotal(), page.getSize(),page.getCurrent());
    }


    // 编辑或者新增  id
    @PostMapping("/save")
    @ApiOperation(value = "编辑或者新增", notes = "编辑或者新增")
    public Result<String> editOrAdd(@Valid @RequestBody RoleDto roleDto) {

//        throw new CustomException(502,"自定义异常");

        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);

        roleService.saveOrUpdate(role);
        return new Result<>().success("新增/编辑成功");
    }


    // 批量删除
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除", notes = "批量删除")
    public Result<String> delete(@RequestBody List<Long> ids) {

        for (int i = 0; i < ids.size(); i++) {
            Role role = roleMapper.selectRole(ids.get(i));
            if (role.getUserList().size() != 0) {
                String str = ids.get(i)+"角色下有用户，不能删除";
                throw new  CustomException(str);
            }
        }

        roleService.removeByIds(ids);
        return new Result<>().success("删除成功");
    }


    // 角色信息（角色和用户）
    @GetMapping("/roleInfo")
    @ApiOperation(value = "角色信息", notes = "角色信息")
    public Result<Role> roleInfo(@RequestParam Long roleId) {
        Role role = roleMapper.selectRole(roleId);
        return new Result<>().success(role);
    }
}
