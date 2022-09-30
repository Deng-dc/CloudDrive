package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.mapper.UserMapper;
import com.afk.cloudrive.pojo.User;
import com.afk.cloudrive.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: dengcong
 * @Date: 2022/9/15 - 09 - 15 - 15:57
 * @Description: com.afk.cloudrive.service.impl
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${file-server.avatar-dir}")
    private String AVATAR_DIR;

    @Value("${file-server.avatar-access-folder}")
    private String AVATAR_ACCESS_FOLDER;

    @Value("${ip-address}")
    private String IP_ADDRESS;

    @Value("${server.port}")
    private String PORT;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public String uploadAvatar(MultipartFile multipartFile, String username) {
        String avatarDir = AVATAR_DIR + username + "\\";
        String filename = multipartFile.getOriginalFilename();
        System.out.println("头像存放的目录 : " + avatarDir);
        File path = new File(avatarDir);
        if (! path.exists()) {
            path.mkdirs();
        }
        File file = new File(avatarDir + filename);
        try {
            multipartFile.transferTo(file);
            String avatarHttpUrl = getAvatarHttpUrl(username, filename);
            Boolean updateResult = updateUserAvatar(avatarHttpUrl, username);
            if (updateResult) {
                return avatarHttpUrl;
            } else {
                throw new BusinessException(ResultEnum.UPLOAD_AVATAR_FAILED);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ResultEnum.UPLOAD_AVATAR_FAILED);
        }
    }

    private String getAvatarHttpUrl(String username, String filename) {
        return "http://" + IP_ADDRESS + ":" + PORT + AVATAR_ACCESS_FOLDER + username + "/" + filename;
    }

    @Override
    public Boolean updateUserAvatar(String avatarHttpUrl, String username) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getUsername, username);
        User user = new User();
        user.setFaceImg(avatarHttpUrl);
        userMapper.update(user, updateWrapper);
        return true;
    }
}
