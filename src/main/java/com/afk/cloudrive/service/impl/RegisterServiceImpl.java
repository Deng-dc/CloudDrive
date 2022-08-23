package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.mapper.UserMapper;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.service.RegisterService;
import com.afk.cloudrive.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 14:52
 * @Description: com.afk.service.impl 注册服务实现
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String userRegister(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new BusinessException(ResultEnum.EMPTY_USERNAME);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new BusinessException(ResultEnum.EMPTY_PASSWORD);
        }
        User selectedUser = userMapper.selectByUsername(user.getUsername());
        if (selectedUser != null) {
            // 该用户名已存在
            throw  new BusinessException(ResultEnum.USERNAME_EXISTS);
        } else {
            // 为每一位注册的用户生成一个唯一的id
            user.setId(IdUtil.getUUID());
            // 使用md5加密用户密码
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userMapper.insert(user);
            System.out.println("注册的用户名为 : " + user.getUsername());
            return user.getUsername();
        }
    }
}
