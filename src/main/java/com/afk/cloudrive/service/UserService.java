package com.afk.cloudrive.service;

import com.afk.cloudrive.pojo.User;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 用户上传头像
     * @param multipartFile
     * @param username
     * @return
     */
    String uploadAvatar(MultipartFile multipartFile, String username);

    /**
     * 更新用户表的头像字段
     * @param avatarHttpUrl
     * @param username
     * @return
     */
    Boolean updateUserAvatar(String avatarHttpUrl, String username);
}
