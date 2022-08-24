package com.afk.cloudrive.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 10:14
 * @Description: com.afk.cloudrive.pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_file")
public class CloudFile {
    /**
     * id
     */
    private String id;

    /**
     * 上传文件的用户
     */
    private String username;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件在服务器存储的路径
     */
    private String filePath;

    /**
     * 文件上传的时间戳
     */
    private Date fileUploadTime;
}
