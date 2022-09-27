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
     * 上一次修改时间
     */
    private String lastModifyTime;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件类型对应的编码
     * 文件夹 : 0
     * 图片 : 1
     * 文档 : 2
     * 音频 : 3
     * 视频 : 4
     * 未知 : 5
     */
    private String fileTypeCode;
}
