package com.afk.cloudrive.controller;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.UserService;
import com.afk.cloudrive.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dengcong
 * @Date: 2022/9/15 - 09 - 15 - 15:55
 * @Description: com.afk.cloudrive.controller
 */
@RestController
@Api(tags = "用户")
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation("获取用户信息")
    public ResponseMessage getUserInfo(String token) {
        Boolean verifyToken = TokenUtil.verifyToken(token);
        if (verifyToken) {
            String username = TokenUtil.getUsername(token);
            User userInfo = userService.getUserInfo(username);
            return ResponseMessage.success(userInfo);
        } else {
            throw  new BusinessException(ResultEnum.TOKEN_ERROR);
        }
    }
}
