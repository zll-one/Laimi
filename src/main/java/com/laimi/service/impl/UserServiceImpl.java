package com.laimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laimi.mapper.UserMapper;
import com.laimi.pojo.User;
import com.laimi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
