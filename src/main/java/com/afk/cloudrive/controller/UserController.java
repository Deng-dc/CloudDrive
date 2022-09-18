package com.afk.cloudrive.controller;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.service.UserService;
import com.afk.cloudrive.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/9/15 - 09 - 15 - 15:55
 * @Description: com.afk.cloudrive.controller
 */
@RestController
@Api(tags = "用户")
@CrossOrigin
public class UserController {

    @Value("${file-server.home-dir}")
    private String sysHomeDir;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    /**
     * 获取用户的信息
     * @param token
     * @return
     */
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

    /**
     * 获取用户根目录下的文件列表
     * @param username
     * @return
     */
    @RequestMapping(value = "/drive/{username}/", method = RequestMethod.GET)
    @ApiOperation("获取用户根目录下的所有文件")
    public ResponseMessage listUserHomeFiles(@PathVariable("username") String username) {
        String homeDir = sysHomeDir + username + "\\";
        Set<String> homeDirFiles = fileService.listFiles(homeDir);
        return ResponseMessage.success(homeDirFiles);
    }
}