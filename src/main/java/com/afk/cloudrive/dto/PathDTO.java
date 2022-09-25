package com.afk.cloudrive.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dengcong
 * @Date: 2022/9/25 - 09 - 25 - 0:02
 * @Description: com.afk.cloudrive.dto 用于接收前端传入当前路径
 */
@Data
@NoArgsConstructor
public class PathDTO {
    /**
     * 路径id
     */
    String id;

    /**
     * 路径名
     */
    String path;
}
