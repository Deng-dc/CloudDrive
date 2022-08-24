package com.afk.cloudrive.service;

import java.util.Date;
import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 10:13
 * @Description: com.afk.cloudrive.service
 */
public interface FileService {
    /**
     * 查看此目录下的所有文件和目录
     * @param dir
     * @return
     */
    Set<String> listFiles(String dir);

    /**
     * 在数据库中存储文件
     * @param originalFileName
     * @param filePath
     * @param username
     * @param timestamp
     * @return
     */
    Boolean storeFile(String originalFileName, String filePath, String username, Date timestamp);

    /**
     * 拿到文件的http链接
     * @param fileTimestamp
     * @return
     */
    String getFileHttpUrl(String fileTimestamp);

    /**
     * 重命名重名文件
     * @param filename
     * @param date
     * @return
     */
    String renameFile(String filename, Date date);
}
