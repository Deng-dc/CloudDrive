package com.afk.cloudrive.controller;

import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 19:42
 * @Description: com.afk.controller
 */
@RestController
@Api(tags = "用户登录")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private FileService fileService;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public ResponseMessage userLogin(@RequestBody User user) {
        String token = loginService.userLogin(user.getUsername(), user.getPassword());
        return ResponseMessage.success(token);
    }
}
