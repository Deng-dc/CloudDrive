package com.afk.cloudrive.service;

import com.afk.cloudrive.pojo.User;

import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 19:42
 * @Description: com.afk.cloudrive.service
 */
public interface LoginService {
    /**
     * 用户登录, 生成一个token返回给客户端
     * @param username
     * @param password
     * @return
     */
    String userLogin(String username, String password);

    /**
     * 校验token
     * @param token
     * @return
     */
    Boolean checkToken(String token);
    /**
     * 得到用户云盘的根目录
     * @param username
     * @return
     */
    String getUserDrive(String username);
}
