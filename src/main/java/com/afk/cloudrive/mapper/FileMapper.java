package com.afk.cloudrive.mapper;

import com.afk.cloudrive.pojo.CloudFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 11:00
 * @Description: com.afk.cloudrive.mapper
 */
@Mapper
@Repository
public interface FileMapper extends BaseMapper<CloudFile> {
}
