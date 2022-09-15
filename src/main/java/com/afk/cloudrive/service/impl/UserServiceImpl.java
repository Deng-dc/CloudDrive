package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.mapper.UserMapper;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: dengcong
 * @Date: 2022/9/15 - 09 - 15 - 15:57
 * @Description: com.afk.cloudrive.service.impl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }
}
