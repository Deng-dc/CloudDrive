package com.afk.cloudrive.service;


import com.afk.cloudrive.pojo.User;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 14:48
 * @Description: com.afk.service 注册服务接口
 */
public interface RegisterService {
    /**
     * 用户注册
     * 注册成功则返回其注册的用户名，注册失败则抛出相应的业务异常
     * @param user
     * @return
     */
    String userRegister(User user);
}
