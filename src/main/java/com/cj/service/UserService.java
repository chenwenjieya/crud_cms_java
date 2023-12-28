package com.cj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.User;
import com.cj.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
}
