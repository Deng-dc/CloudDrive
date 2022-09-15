package com.afk.cloudrive.service;

import com.afk.cloudrive.pojo.User;

/**
 * @Author: dengcong
 * @Date: 2022/9/15 - 09 - 15 - 15:56
 * @Description: com.afk.cloudrive.service
 */
public interface UserService {
    /**
     * 通过用户名得到用户信息
     * @param username
     * @return
     */
    User getUserInfo(String username);
}
