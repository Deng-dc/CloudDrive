package com.afk.cloudrive.service;

import com.afk.cloudrive.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 10:13
 * @Description: com.afk.cloudrive.service
 */
public interface FileService {

    /**
     * 上传文件的服务器，其目录为currentDir
     * @param multipartFile
     * @param currentDir
     * @param username
     * @return
     */
    Boolean uploadFile(MultipartFile multipartFile, String currentDir, String username);

    /**
     * 列出对应dir下所有的文件
     * @param dir
     * @return
     */
    ArrayList<FileVO> listFiles(String dir);

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

    /**
     * 对应path下新建文件夹
     * @param username
     * @param path
     * @return
     */
    Boolean createNewDir(String username, String path);
}
