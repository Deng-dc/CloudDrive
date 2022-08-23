package com.afk.cloudrive.mapper;

import com.afk.cloudrive.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 14:40
 * @Description: com.afk.mapper
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
