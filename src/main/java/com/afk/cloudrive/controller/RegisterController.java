package com.afk.cloudrive.controller;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 16:46
 * @Description: com.afk.controller
 */
@RestController
@Api(tags = "用户注册")
@CrossOrigin
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public ResponseMessage userRegister(@RequestBody User user) {
        registerService.userRegister(user);
        registerService.makeUserDrive(user.getUsername());
        return ResponseMessage.success(ResultEnum.SUCCESS);
    }
}
