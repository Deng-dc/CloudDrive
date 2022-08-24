package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.mapper.UserMapper;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.service.LoginService;
import com.afk.cloudrive.service.RegisterService;
import com.afk.cloudrive.util.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 19:42
 * @Description: com.afk.cloudrive.service.impl
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterService registerService;

    @Value("${file-server.home-dir}")
    private String sysHomeDir;

    @Override
    public String userLogin(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            // 用户未注册
            throw new BusinessException(ResultEnum.USER_IS_NOT_REGISTERED);
        }
        if (! user.getPassword().equals( DigestUtils.md5DigestAsHex(password.getBytes())) ) {
            // 密码错误
            throw new BusinessException(ResultEnum.PASSWORD_ERROR);
        }
        return TokenUtil.generateTokenByUsername(username, password);
    }

    @Override
    public Boolean checkToken(String token) {
        return TokenUtil.verifyToken(token);
    }

    @Override
    public String getUserDrive(String username) {
        if (registerService.makeUserDrive(username)) {
            return sysHomeDir + username + "\\";
        } else {
            throw new BusinessException(ResultEnum.USER_DRIVE_NOT_EXISTS);
        }
    }
}
