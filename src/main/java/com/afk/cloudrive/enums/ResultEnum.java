package com.afk.cloudrive.enums;

import lombok.Getter;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 15:57
 * @Description: 定义返回的枚举类
 */
public enum ResultEnum {
    SUCCESS(1000, "成功"),
    NEED_LOGIN(1001, "用户未登录"),
    EMPTY_USERNAME(1002, "用户名为空"),
    EMPTY_PASSWORD(1003, "密码为空"),
    USERNAME_EXISTS(1004, "用户名已被注册"),
    USER_IS_NOT_REGISTERED(1005, "用户未注册"),
    PASSWORD_ERROR(1006, "密码错误");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
