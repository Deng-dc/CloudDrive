package com.afk.cloudrive.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 14:27
 * @Description: com.afk.pojo 定义实体类 User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    /**
     * id
     */
    private String id;

    /**
     * 用户名  唯一
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像url
     */
    private String faceImg;

    /**
     * 用户昵称
     */
    private String nickname;
}
