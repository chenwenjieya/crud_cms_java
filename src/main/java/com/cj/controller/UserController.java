package com.cj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cj.common.JwtUtil;
import com.cj.common.PageResult;
import com.cj.common.Result;
import com.cj.dto.user.UserDto;
import com.cj.dto.user.UserLogin;
import com.cj.dto.user.UserQuery;
import com.cj.entity.User;
import com.cj.exception.CustomException;
import com.cj.mapper.UserMapper;
import com.cj.service.impl.UserService;
import com.cj.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(value = "用户管理", tags= "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserMapper userMapper;

    // 新增
    @ApiOperation(value = "新增用户", notes = "增加单个用户")
    @PostMapping("/addUser")
    public Result<String> addUser(@Valid @RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
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
    public Result<String> updateuser(@Valid @RequestBody UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        userService.saveOrUpdate(user);
        return new Result<>().success("更新用户成功");
    }

    // 查询
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/selectPage")
    public PageResult<List<User>> findPage(@RequestBody UserQuery userQuery) {

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

        return new PageResult<>().success(page.getRecords(),page.getTotal(), page.getSize(), page.getCurrent());
    }


    /**
     * 登陆接口
     */
    @ApiOperation(value = "登陆接口", notes = "登陆")
    @PostMapping("/login")
    public Result<UserLoginVo> login(@RequestBody @Valid UserLogin userLogin) {
        // lambda 查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        // 查询出账号密码都匹配的信息
        wrapper.eq(User::getUsername, userLogin.getUsername())
                .eq(User::getPassword, userLogin.getPassword())
                .last("limit 1");  // 防止查出来多条报错

        User user = userService.getOne(wrapper);

        // 返回给用户的数据格式
        UserLoginVo userLoginVo = new UserLoginVo();

        if (user != null) {
            // 生成token 存储到redis中
            String token = JwtUtil.generateToken(user);

            userLoginVo.setToken(token);
            userLoginVo.setUsername(user.getUsername());
            userLoginVo.setUserId(user.getId());
            userLoginVo.setRoleId(user.getRoleId());

            // 存储到redis中，缓存时间一个小时
//            stringRedisTemplate.opsForValue().set("token:" + user.getId(), token, 1, TimeUnit.HOURS);
        } else{
            throw new CustomException("用户名或密码错误");
        }

        return new Result<>().success(userLoginVo);
    }


    /**
     * 根据用户的id获取用户的信息(包括角色信息)
     */
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @PostMapping("/userInfo")
    public Result<User> getUserInfo(HttpServletRequest request) {
        // 获取用户id
        Long userId = JwtUtil.getUserId(request);
        // 查询用户信息
        User userOne = userMapper.getUserOne(userId);

        return new Result<>().success(userOne);
    }

}
