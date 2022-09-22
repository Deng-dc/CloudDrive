package com.afk.cloudrive.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dengcong
 * @Date: 2022/9/22 - 09 - 22 - 20:07
 * @Description: com.afk.cloudrive.vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    /**
     * 文件名
     */
    private String filename;

    /**
     * 目录还是文件
     */
    private boolean whetherDir;

    /**
     * 上一次修改时间
     */
    private String lastModifyTime;

    /**
     * 文件大小
     */
    private String fileSize;
}
